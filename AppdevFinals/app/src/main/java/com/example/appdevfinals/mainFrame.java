package com.example.appdevfinals;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class mainFrame extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private EditText postText;
    private ImageView selectImage;
    private Button buttonPost;
    private RecyclerView recyclerViewPosts;

    private Uri selectedImageUri;
    private ActivityResultLauncher<Intent> selectImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        postText = findViewById(R.id.postText);
        selectImage = findViewById(R.id.selectImage);
        buttonPost = findViewById(R.id.buttonPost);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);

        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        // Load posts from Firestore
        loadPosts();

        // Select Image on Click
        selectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectImageLauncher.launch(intent);
        });

        selectImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                selectedImageUri = result.getData().getData();
                selectImage.setImageURI(selectedImageUri);
            }
        });

        // Post Text and Image
        buttonPost.setOnClickListener(v -> {
            String text = postText.getText().toString();
            if (text.isEmpty() && selectedImageUri == null) {
                Toast.makeText(this, "Please enter text or select an image.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedImageUri != null) {
                uploadImageAndPost(text);
            } else {
                createPost(text, null);
            }
        });
    }

    private void loadPosts() {
        db.collection("posts").orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Post> posts = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Post post = document.toObject(Post.class);
                            post.setId(document.getId()); // Set the document ID to the post object
                            posts.add(post);
                        }
                        PostAdapter adapter = new PostAdapter(posts, this);
                        recyclerViewPosts.setAdapter(adapter);
                        adapter.notifyDataSetChanged(); // Ensure the adapter updates the view
                    } else {
                        Toast.makeText(this, "Failed to load posts.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadImageAndPost(String text) {
        if (selectedImageUri == null) {
            createPost(text, null);
            return;
        }

        StorageReference imageRef = storage.getReference().child("post_images/" + System.currentTimeMillis());
        imageRef.putFile(selectedImageUri)
                .addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    createPost(text, imageUrl);
                }))
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to upload image.", Toast.LENGTH_SHORT).show());
    }

    private void createPost(String text, String imageUrl) {
        String userId = mAuth.getCurrentUser().getUid();
        String userName = mAuth.getCurrentUser().getDisplayName();
        Post post = new Post(userId, userName, text, imageUrl);

        db.collection("posts").add(post)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Post created.", Toast.LENGTH_SHORT).show();
                    postText.setText("");
                    selectImage.setImageDrawable(null);
                    selectedImageUri = null;
                    loadPosts();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to create post.", Toast.LENGTH_SHORT).show());
    }
}


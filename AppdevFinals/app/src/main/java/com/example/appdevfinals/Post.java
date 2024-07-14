package com.example.appdevfinals;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id; // Add this field
    private String userId;
    private String userName;
    private String text;
    private String imageUrl;
    private List<Comment> comments;

    // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    public Post() {}


    public Post(String userId, String userName, String text, String imageUrl) {
        this.userId = userId;
        this.userName = userName;
        this.text = text;
        this.imageUrl = imageUrl;
        this.comments = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public void addComment(Comment comment) { comments.add(comment); }
}

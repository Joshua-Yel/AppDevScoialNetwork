package com.example.appdevfinals;

public class Comment {
    private String userId;
    private String userName;
    private String text;

    // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    public Comment() {}

    public Comment(String userId, String userName, String text) {
        this.userId = userId;
        this.userName = userName;
        this.text = text;
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getText() { return text; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setText(String text) { this.text = text; }
}

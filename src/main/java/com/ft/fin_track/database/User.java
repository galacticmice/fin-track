package com.ft.fin_track.database;

public class User {
    private int userID;
    private String username;
    private String email;
    private String password;
    private String dateCreated;

    public User(int userID, String username, String email, String password, String dateCreated) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}

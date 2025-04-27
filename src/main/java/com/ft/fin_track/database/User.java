package com.ft.fin_track.database;

import java.sql.Timestamp;

public class User {
    private Integer userID; // primary key, auto-increment: null-->let db handle
    private String username;
    private String email;
    private String password;
    private final Timestamp creation_time; // creation time, not user modifiable

    public User(Integer userID, String username, String email, String password, Timestamp creation_time) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creation_time = creation_time;
    }

    public User(String username, String email, String password) {
        this(null, username, email, password, new Timestamp(System.currentTimeMillis()));
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

    public Timestamp getCreationTime() {
        return creation_time;
    }

}

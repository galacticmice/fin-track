package com.ft.fin_track.database;

import java.sql.Timestamp;

// Transaction activity
public class Activity {
    private Integer activity_id; // primary key, auto increment: null-->let db handle
    private final Timestamp entry_time;
    private final int user_id;
    private int category_id;
    private boolean activity_type; // true for income, false for expense
    private double amount;
    private String description;

    // blanket constructor for retrieving activity
    public Activity(Integer activity_id, Timestamp entry_time, int user_id, int category_id, boolean activity_type, double amount, String description) {
        this.activity_id = activity_id;
        this.entry_time = entry_time;
        this.user_id = user_id;
        this.category_id = category_id;
        this.activity_type = activity_type;
        this.amount = amount;
        this.description = description;
    }

    // constructor for adding activity (no activity_id, entry_time --> not user modifiable values)
    public Activity(int user_id, int category_id, boolean activity_type, double amount, String description) {
        this(null, new Timestamp(System.currentTimeMillis()), user_id, category_id, activity_type, amount, description);
    }

    public int getActivity_id() {
        return activity_id;
    }

    public Timestamp getEntryTime() {
        return entry_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public boolean getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(boolean activity_type) {
        this.activity_type = activity_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

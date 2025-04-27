package com.ft.fin_track.database;

import java.sql.Timestamp;

// Transaction activity --> named Activity to avoid confusion between SQL transctions
public class Activity {
    private Integer activity_id; // primary key, auto increment: null-->let db handle
    private final Timestamp entry_time;
    private int user_id;
    private int category_id;
    private char type; // 'W' for withdraw, 'D' for deposit
    private double amount;
    private String description;
    private int recur_days;

    public Activity(int user_id, int category_id, char type, double amount, String description, int recur_days) {
        this.entry_time = new Timestamp(System.currentTimeMillis());
        this.user_id = user_id;
        this.category_id = category_id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.recur_days = recur_days;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public Timestamp getEntryTime() {
        return entry_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
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

    public int getRecur_days() {
        return recur_days;
    }

    public void setRecur_days(int recur_days) {
        this.recur_days = recur_days;
    }
}

package com.ft.fin_track.database;

// Transaction activity --> named Activity to avoid confusion between SQL transctions
public class Activity {
    private int activity_id;
    private String date_time;
    private int user_id;
    private int category_id;
    private double amount;
    private String description;
    private int recur_days;

    public Activity(int activity_id, String date_time, int user_id, int category_id, double amount, String description, int recur_days) {
        this.activity_id = activity_id;
        this.date_time = date_time;
        this.user_id = user_id;
        this.category_id = category_id;
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

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
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

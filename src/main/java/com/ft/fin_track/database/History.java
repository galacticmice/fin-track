package com.ft.fin_track.database;

import java.sql.Date;

public class History {
    private final int user_id;
    private int year;
    private int month;
    private double budget;

    // deciding between storing calculated data here,
    // or calculate on-demand with query


    public History(int user_id, int year, int month, double budget) {
        this.user_id = user_id;
        this.year = year;
        this.month = month;
        this.budget = budget;
    }



    public int getUser_id() {
        return user_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}

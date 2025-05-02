package com.ft.fin_track.database;

import java.sql.Date;
import java.time.LocalDate;

public class History {
    private final int user_id;
    private final Date month_year;
    private double budget;

    /// stores user's set budget for that month
    /// 1. user changes data in April(this month), we change budget data for April
    /// 2. come May --> April data cannot be modified anymore. It exists only in history
    /// 3. so if user queries last month's data, we can show April stats
    /// 4. on the first of every month, run script to create
    public History(int user_id, Date month_year, double budget) {
        this.user_id = user_id;
        // Convert java.sql.Date to java.time.LocalDate
        LocalDate localDate = month_year.toLocalDate();
        // Set the day to the first of the month
        LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
        // Convert back to java.sql.Date and assign
        this.month_year = Date.valueOf(firstDayOfMonth);
        this.budget = budget;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getMonth_year() {
        return month_year;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}

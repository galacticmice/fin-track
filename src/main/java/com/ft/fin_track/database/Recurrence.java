package com.ft.fin_track.database;

import java.sql.Date;

public class Recurrence {
    private final Integer recurrence_id; // primary key, auto increment: null-->let db handle
    private final int user_id;
    private final int activity_id; // id to get activity details from
    private final int interval_days; // number of days between recurrences
    private final Date last_change; // last charged date, not user modifiable


    public Recurrence(Integer recurrence_id, int user_id, int activity_id, int interval_days, Date last_change) {
        this.recurrence_id = recurrence_id;
        this.user_id = user_id;
        this.activity_id = activity_id;
        this.interval_days = interval_days;
        this.last_change = last_change;
    }

    public Recurrence(int user_id, int activity_id, int interval_days) {
        this(null, user_id, activity_id, interval_days, new Date(System.currentTimeMillis()));
    }

    public int getRecurrence_id() {
        return recurrence_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public int getInterval_days() {
        return interval_days;
    }

    public Date getLast_change() {
        return last_change;
    }
}

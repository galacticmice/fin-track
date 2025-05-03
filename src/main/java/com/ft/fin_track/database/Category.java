package com.ft.fin_track.database;

public class Category {
    private final int category_id;
    private final String description;

    public Category(int category_id, String description) {
        this.category_id = category_id;
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getDescription() {
        return description;
    }
}

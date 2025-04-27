package com.ft.fin_track.database;

public class Category {
    private final int category_id;
    private final String type;
    private final String description;

    public Category(int category_id, String type, String description) {
        this.category_id = category_id;
        this.type = type;
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}

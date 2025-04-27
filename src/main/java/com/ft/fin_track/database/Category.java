package com.ft.fin_track.database;

public class Category {
    private int category_id;
    private String type;
    private String description;

    public Category(int category_id, String type, String description) {
        this.category_id = category_id;
        this.type = type;
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

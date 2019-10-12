package com.chat.server.database;

public class RoomDatabaseRow {
    public String name;
    public String description;
    public String imagePath;

    public RoomDatabaseRow(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public RoomDatabaseRow(String name, String description) {
        this(name, description, "");
    }

    public RoomDatabaseRow() {
    }

}

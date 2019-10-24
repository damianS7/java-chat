package com.chat.common;

public class BasicRoom {
    public String name;
    public String description;

    public BasicRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BasicRoom() {
        this("", "");
    }

}

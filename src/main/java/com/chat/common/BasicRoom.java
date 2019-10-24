package com.chat.common;

public class Room {

    public String name;
    public String description;

    public Room(String name, String description) {
	this.name = name;
	this.description = description;
    }

    public Room() {
	this("", "");
    }

}

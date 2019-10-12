package com.chat.server.database;

public class UserDatabaseRow {
    public String username;
    public String password;
    public String role;

    public UserDatabaseRow(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDatabaseRow() {
    }

}

package com.chat.server.database;

public class AccountDatabaseRow {
    public String username;
    public String password;
    public String role;

    public AccountDatabaseRow(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AccountDatabaseRow() {
    }

}

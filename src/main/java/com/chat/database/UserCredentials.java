package com.chat.database;

import com.chat.common.UserRole;

public class UserCredentials {
    public String username;
    public String password;
    public UserRole role;

    public UserCredentials(String username, String password) {
        this(username, password, UserRole.USER);
    }

    public UserCredentials(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

package com.chat.database;

import com.chat.common.AccountRole;

public class UserCredentials {
    public String username;
    public String password;
    public AccountRole role;

    public UserCredentials(String username, String password) {
        this(username, password, AccountRole.USER);
    }

    public UserCredentials(String username, String password, AccountRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

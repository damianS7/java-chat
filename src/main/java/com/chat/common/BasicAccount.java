package com.chat.common;

public class BasicAccount {
    private String username;
    private String password;
    private AccountRole role;

    public BasicAccount(String username, String password, AccountRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public BasicAccount(String username, String password, String role) {
        this(username, password);
        setRoleString(role);
    }

    public BasicAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AccountRole.USER;
    }

    public BasicAccount() {
        this("", "", AccountRole.USER);
    }

    private boolean isValidRole(String roleString) {
        for (AccountRole role : AccountRole.values()) {
            if (role.toString().equalsIgnoreCase(roleString)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

    public void setRoleString(String role) {
        if (!isValidRole(role)) {
            setRole(AccountRole.USER);
        }

        setRole(AccountRole.valueOf(role));
    }
}

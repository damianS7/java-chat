package com.chat.client;

import java.util.ArrayList;
import java.util.List;

import com.chat.common.Room;

public class ClientRoom extends Room {
    private List<String> users = new ArrayList<String>();

    public List<String> getUsers() {
        return users;
    }
}

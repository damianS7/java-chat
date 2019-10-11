package com.chat.client;

public interface Chat {

    public String getUsername();

    public void exitRoom(String roomName);

    public void enterRoom(String roomName);

    public void sendMessageToRoom(String from, String roomName, String message);

    public void sendMessageToConversation(String from, String to,
            String message);

    public void authenticate(String user, String password);
}

package com.chat.server;

public interface Server {
    boolean start();

    boolean stop();

    boolean isOnline();

    void setPort(int port);

    int getPort();

    void setAddress(String address);

    String getAddress();
}

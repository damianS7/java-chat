package com.chat.network;

import java.net.Socket;

public interface Connection {
    public void close();

    public String getAddress();

    public boolean isAlive();

    public Socket getConnectionSocket();
}

package com.chat.network;

import java.net.Socket;

public interface BasicSocket {
    public void close();

    public String getAddress();

    public boolean isAlive();

    public Socket getSocket();
}

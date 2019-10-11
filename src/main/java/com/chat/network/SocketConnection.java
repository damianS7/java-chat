package com.chat.network;

import java.io.IOException;
import java.net.Socket;

/**
 */
public abstract class SocketConnection implements Connection, ConnectionIO {
    protected Socket socket;
    protected String address;

    public SocketConnection(Socket socket) {
        this.socket = socket;
        this.address = socket.getInetAddress().getHostAddress();
    }

    public String getAddress() {
        return address;
    }

    public Socket getConnectionSocket() {
        return socket;
    }

    public boolean isAlive() {
        if (socket.isClosed()) {
            return false;
        }
        return true;
    }

    public void close() {

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
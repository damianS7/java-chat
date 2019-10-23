package com.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public abstract class Client {
    protected Socket socket;

    // Conecta con el servidor
    public boolean connect() {
        try {
            socket = new Socket(ClientApplication.config.getAddress(),
                            ClientApplication.config.getPort());
            return true;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Desconecta y cierra la conexion con el servidor
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para comprobar que nuestro cliente sigue conectado con el servidor
    public boolean isConnected() {
        if(socket == null) {
            return false;
        }
        return !socket.isClosed();
    }
}
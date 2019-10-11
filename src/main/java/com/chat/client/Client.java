package com.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.chat.network.Packet;

public abstract class Client {
    protected ClientConnection connection;

    // Conecta con el servidor
    public boolean connect() {
        try {
            connection = new ClientConnection(
                    new Socket(ClientApplication.config.getAddress(),
                            ClientApplication.config.getPort()));

            new Thread(connection).start();

            if (connection.isAlive()) {
                ClientApplication.ui.setConnectionStatus("Connected");
                return true;
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendPacket(Packet packet) {
        connection.writePacket(packet);
    }

    // Desconecta y cierra la conexion con el servidor
    public void disconnect() {
        connection.close();
    }

    // Metodo para comprobar que nuestro cliente sigue conectado con el servidor
    public boolean isConnected() {
        return connection.isAlive();
    }

    // Metodo para comprobar si el usuario esta autentificado
    public boolean isAuth() {
        return true;
    }

}
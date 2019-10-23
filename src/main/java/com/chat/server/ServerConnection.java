package com.chat.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.chat.network.SocketConnection;
import com.chat.network.packets.Packet;

public class ServerConnection extends SocketConnection {
    private int timeout = 120;

    public ServerConnection(Socket socket) {
        super(socket);
    }

    @Override
    public void writePacket(Packet packet) {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(packet);
            oos.flush();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Packet readPacket() {
        Packet packet = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            packet = (Packet) ois.readObject();
        } catch (SocketException e) {
            // Conexion perdida
            close();
        } catch (EOFException e) {
            // close();
            // conexion perdida
            // timeout 0
            close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }
}

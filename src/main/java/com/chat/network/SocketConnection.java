package com.chat.network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.chat.network.packets.Packet;

/**
 */
public abstract class SocketConnection implements Runnable, BasicSocket, SocketIO, PacketHandler {
    protected String address;
    protected Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
        this.address = socket.getInetAddress().getHostAddress();
        new Thread(this).start();
    }

    public String getAddress() {
        return address;
    }

    public Socket getSocket() {
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

    @Override
    public void writePacket(Packet packet) {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(packet);
            oos.flush();
        } catch (SocketException e) {
            // Socket cerrado, intentar reconexion ?? desconectar cliente
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
            return packet;
        } catch (SocketException e) {
            // Conexion perdida
        } catch (EOFException e) {
            // close();
            // conexion perdida
            // timeout 0
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        close();
        return null;
    }

    @Override
    public void run() {
        while (isAlive()) {
            Packet packet = readPacket();
            processPacket(packet);
        }
    }
}
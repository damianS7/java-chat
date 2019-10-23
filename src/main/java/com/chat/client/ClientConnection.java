package com.chat.client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.chat.network.SocketConnection;
import com.chat.network.packets.Packet;

public class ClientConnection extends SocketConnection {

    public ClientConnection(Socket socket) {
        super(socket);
    }

    @Override
    public void writePacket(Packet packet) {

        if (socket.isClosed() || packet == null) {
            return;
        }

        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.flush();
            oos.writeObject(packet);
            oos.flush(); // !
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Packet readPacket() {
        if (!isAlive()) {
            return null;
        }

        Packet packet = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream());
            packet = (Packet) ois.readObject();
            return packet;
        } catch (SocketException e) {
            // e.printStackTrace();
            // close();
            // throw new Exception("Se produjo un problema al intentar"
            // + " recibir un paquete, es posible que la conexion este
            // cerrada");
        } catch (EOFException e) {
            e.printStackTrace();
            // close();
            // throw new Exception("Connection lost.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packet;
    }
}

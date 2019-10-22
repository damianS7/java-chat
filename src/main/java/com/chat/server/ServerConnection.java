package com.chat.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.chat.network.Packet;
import com.chat.network.SocketConnection;

public class ServerConnection extends SocketConnection implements Runnable {
    private ServerPacketHandler packetHandler;
    private int timeout = 120;

    public ServerConnection(Socket socket) {
	super(socket);
	packetHandler = new ServerPacketHandler();
    }

    @Override
    public void writePacket(Packet packet) {
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
	Packet packet = null;
	try {
	    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	    packet = (Packet) ois.readObject();
	} catch (SocketException e) {
	    // e.printStackTrace();
	    // Conexion perdida
	} catch (EOFException e) {
	    e.printStackTrace();
	    // close();
	    // conexion perdida
	    // timeout 0
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return packet;
    }

    @Override
    public void run() {
	while (isAlive()) {
	    Packet p = readPacket();

	    if (p != null) {
		processPacket(new DataPacket(this, p));
	    }
	}
    }

}

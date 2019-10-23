package com.chat.client;

import java.net.Socket;

import com.chat.network.SocketConnection;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;
import com.chat.network.packets.PongPacket;

/**
 * Esta clase gestiona las comunicaciones entre el cliente y el servidor a
 * traves de la red.
 * 
 * Socket Cliente <-> Servidor
 * 
 * @author Damian
 *
 */
public class ClientServerSocket extends SocketConnection {

    public ClientServerSocket(Socket socket) {
        super(socket);
    }

    @Override
    public void processPacket(Packet packet) {

        if (packet instanceof PingPacket) {
            System.out.println("Ping received on client! sending back Pong.");
            writePacket(new PongPacket());

        }

        if (packet instanceof PongPacket) {
            System.out.println("Pong received on client! keep alive.");
        }
    }

}

package com.chat.server;

import java.net.Socket;

import com.chat.network.SocketConnection;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;
import com.chat.network.packets.PongPacket;

/**
 * A traves de esta clase se gestionan las comunicaciones entre el servidor y el
 * cliente.
 * 
 * Socket SERVIDOR <-> CLIENTE
 * 
 * @author Damian
 *
 */

public class ServerClientSocket extends SocketConnection {

    public ServerClientSocket(Socket socket) {
        super(socket);
    }

    @Override
    public void processPacket(Packet packet) {

        if (packet instanceof PingPacket) {
            writePacket(new PongPacket());
        }

        if (packet instanceof PongPacket) {
            System.out.println("Pong received on server! keep alive.");
        }

    }

    @Override
    public void run() {
        super.run();
        ServerApplication.ui.log.addLine("Cliente desconectado.");
    }

}

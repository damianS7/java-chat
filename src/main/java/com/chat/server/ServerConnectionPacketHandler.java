package com.chat.server;

import com.chat.network.PacketListener;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;
import com.chat.network.packets.PongPacket;

public class ServerConnectionPacketHandler implements Runnable, PacketListener {
    private ServerConnection connection;
    
    public ServerConnectionPacketHandler(ServerConnection connection) {
        this.connection = connection;
    }
    
    @Override
    public void processPacket(Packet packet) {
        
        if (packet instanceof PingPacket) {
            connection.writePacket(new PongPacket());
        }
        
        if (packet instanceof PongPacket) {
            System.out.println("Pong received on server! keep alive.");
        }
        
    }

    @Override
    public void run() {
        while(connection.isAlive()) {
            System.out.println("Esperando paquete ...");
            Packet receivedPacket = connection.readPacket();
            processPacket(receivedPacket);
        }
        
        System.out.println("ServerConnection end.");
    }
}

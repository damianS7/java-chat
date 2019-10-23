package com.chat.client;

import com.chat.network.PacketListener;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;
import com.chat.network.packets.PongPacket;

public class ClientConnectionPacketHandler implements Runnable, PacketListener {
    private ClientConnection connection;
    
    public ClientConnectionPacketHandler(ClientConnection connection) {
        this.connection = connection;
    }
    
    @Override
    public void processPacket(Packet packet) {
        
        if(packet instanceof PingPacket) {
            System.out.println("Ping received on client! sending back Pong.");
            connection.writePacket(new PongPacket());
        }
        
        if (packet instanceof PongPacket) {
            System.out.println("Pong received on client! keep alive.");
        }
    }

    @Override
    public void run() {
        while (connection.isAlive()) {
            Packet p = null;
            try {
                p = connection.readPacket();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            if (p != null) {
                processPacket(p);
            }
            
            try {
                Thread.sleep(2000);
                //writePacket(new Packet());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        connection.close();
        System.out.println("Conexion terminada");
    }

}

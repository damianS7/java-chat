package com.chat.client;

import com.chat.network.Packet;
import com.chat.network.PacketProcessor;
import com.chat.network.PacketType;

public class ClientPacketProcessor implements PacketProcessor {

    @Override
    public void processPacket(Packet packet) {

    }

    @Override
    public void processOutputPacket(Packet packet) {
        if (packet.getType() == PacketType.JOIN_ROOM_REQUEST) {
            ClientApplication.client.sendPacket(packet);
        }

    }

    @Override
    public void processInputPacket(Packet packet) {
        if (packet.getType() == PacketType.JOIN_ROOM_RESPONSE) {
            // ClientApplication.client.sendPacket(packet);
        }

        if (packet.getType() == PacketType.AUTH_RESPONSE) {

            if (!packet.authSucess) {
                ClientApplication.ui
                        .appendLog("No se pudo verificar tu user/pass");
                ClientApplication.client.disconnect();
            }

            // ClientApplication.client.sendPacket(packet);
        }
    }

    @Override
    public Packet buildPacket(PacketType type) {
        Packet packet = new Packet(type);

        if (packet.getType() == PacketType.JOIN_ROOM_REQUEST) {

        }

        return packet;
    }

}

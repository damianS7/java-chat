package com.chat.network;

import com.chat.network.packets.Packet;

public interface PacketHandler {
    void processPacket(Packet packet);
}

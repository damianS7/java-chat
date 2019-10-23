package com.chat.network;

import com.chat.network.packets.Packet;

public interface PacketListener {
    void processPacket(Packet packet);
}

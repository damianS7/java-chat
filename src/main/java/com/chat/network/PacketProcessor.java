package com.chat.network;

public interface PacketProcessor {
    public void processPacket(Packet packet);

    public void processOutputPacket(Packet packet);

    public void processInputPacket(Packet packet);

    public Packet buildPacket(PacketType type);
    // public void sendPacket();

    // public void receivePacket();

    // public void receiveImage();

    // public void receiveFile();

    // public void receiveBytes();
}

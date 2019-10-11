package com.chat.client;

import java.util.List;

import com.chat.common.Conversation;
import com.chat.common.Room;
import com.chat.database.UserCredentials;
import com.chat.network.Packet;
import com.chat.network.PacketType;

public class ClientChat extends Client implements Chat {
    protected UserCredentials userCredentials;
    protected ClientPacketProcessor packetProcess;
    protected List<Conversation> activeConversations;
    protected List<Room> activeRooms;

    public ClientChat() {
        packetProcess = new ClientPacketProcessor();
    }

    @Override
    public boolean connect() {

        if (!super.connect()) {
            return false;
        }

        authenticate(ClientApplication.config.getUsername(),
                ClientApplication.config.getPassword());

        return connection.isAlive();
    }

    @Override
    public void exitRoom(String roomName) {
        Packet packet = packetProcess.buildPacket(PacketType.EXIT_ROOM_REQUEST);
        packet.roomName = roomName;
        sendPacket(packet);
    }

    @Override
    public void enterRoom(String roomName) {
        Packet packet = packetProcess.buildPacket(PacketType.JOIN_ROOM_REQUEST);
        packet.roomName = roomName;
        sendPacket(packet);
    }

    @Override
    public void sendMessageToRoom(String from, String roomName,
            String message) {
        Packet packet = packetProcess.buildPacket(PacketType.JOIN_ROOM_REQUEST);
        packet.roomName = roomName;
        sendPacket(packet);
    }

    @Override
    public void sendMessageToConversation(String from, String to,
            String message) {
        Packet packet = packetProcess.buildPacket(PacketType.JOIN_ROOM_REQUEST);
        packet.from = from;
        packet.to = to;
        sendPacket(packet);
    }

    @Override
    public void authenticate(String user, String password) {
        userCredentials = new UserCredentials(user, password);
        Packet packet = packetProcess.buildPacket(PacketType.AUTH_REQUEST);
        packet.username = userCredentials.username;
        packet.password = userCredentials.password;
        sendPacket(packet);
    }

    @Override
    public String getUsername() {
        return userCredentials.username;
    }

    public List<String> getActiveConversations() {
        return null;
    }

    public List<String> getActiveRooms() {
        return null;
    }

}

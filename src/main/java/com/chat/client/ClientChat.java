package com.chat.client;

import java.util.List;

import com.chat.common.Conversation;
import com.chat.common.BasicRoom;
import com.chat.database.UserCredentials;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;

public class ClientChat extends Client implements Chat {
    protected ClientServerSocket connection;
    protected UserCredentials userCredentials;
    protected List<Conversation> activeConversations;
    protected List<BasicRoom> activeRooms;

    @Override
    public boolean connect() {
        if (!super.connect()) {
            return false;
        }

        connection = new ClientServerSocket(socket);

        // authenticate(ClientApplication.config.getUsername(),
        // ClientApplication.config.getPassword());

        for (int i = 0; i < 5; i++) {
            connection.writePacket(new PingPacket());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return connection.isAlive();
    }

    public void sendPacket(Packet packet) {
        connection.writePacket(packet);
    }

    @Override
    public void exitRoom(String roomName) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void enterRoom(String roomName) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void sendMessageToRoom(String from, String roomName, String message) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void sendMessageToConversation(String from, String to, String message) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void authenticate(String user, String password) {
        userCredentials = new UserCredentials(user, password);
        // Packet packet = packetProcess.buildPacket(PacketType.AUTH_REQUEST);
        // packet.username = userCredentials.username;
        // packet.password = userCredentials.password;
        Packet packet = new Packet();
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

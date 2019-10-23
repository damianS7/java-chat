package com.chat.client;

import java.util.List;

import com.chat.common.Conversation;
import com.chat.common.Room;
import com.chat.database.UserCredentials;
import com.chat.network.packets.Packet;
import com.chat.network.packets.PingPacket;

public class ClientChat extends Client implements Chat {
    protected ClientConnection connection;
    protected UserCredentials userCredentials;
    protected ClientConnectionPacketHandler packetHandler;
    protected List<Conversation> activeConversations;
    protected List<Room> activeRooms;

    @Override
    public boolean connect() {
        if (!super.connect()) {
            return false;
        }
        
        connection = new ClientConnection(socket);
        packetHandler = new ClientConnectionPacketHandler(connection);
        new Thread(packetHandler).start();


        //authenticate(ClientApplication.config.getUsername(),
                //ClientApplication.config.getPassword());

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
    public void sendMessageToRoom(String from, String roomName,
            String message) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void sendMessageToConversation(String from, String to,
            String message) {
        Packet packet = new Packet();
        sendPacket(packet);
    }

    @Override
    public void authenticate(String user, String password) {
        userCredentials = new UserCredentials(user, password);
        //Packet packet = packetProcess.buildPacket(PacketType.AUTH_REQUEST);
        //packet.username = userCredentials.username;
        //packet.password = userCredentials.password;
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

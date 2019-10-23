package com.chat.network;

import com.chat.network.packets.Packet;

/**
 * Implementacion de operaciones de escritura y lectura sobre un socket
 * 
 * @author Damian
 *
 */
public interface SocketIO {
    /**
     * Envio de paquetes a traves de un socket
     * 
     * @param packet
     */
    public void writePacket(Packet packet);

    /**
     * Si se recibe un paquete a traves del socket se capturara en este metodo
     * 
     * @return
     */
    public Packet readPacket();
}

package com.chat.network;

public interface ConnectionIO {
    /**
     * Envia un objeto a traves del socket
     * 
     * @param p El objeto a enviar debe ser una instancia de DataPacket
     * @return True si envio el paquete
     * @throws ConnectionLostException si la conexion se ha perdido o el socket
     *                                 fue cerrado
     */
    public void writePacket(Packet packet);

    public Packet readPacket();
}

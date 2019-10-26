package com.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Logger;

/*
 * Clase base para implementar el Servidor. 
 */
public abstract class BasicServer implements Server, Runnable {
    private static Logger logger = Logger.getLogger(BasicServer.class.getName());

    // Direccion donde el servidor aceptara los clientes
    private String address;

    // Puerto donde se escuchara
    private int port;

    // Socket principal del servidor
    protected ServerSocket serverSocket;

    // Estado actual del servidor
    private boolean online = false;

    // Devuelve el estado actual del servidor. true para el servidor iniciado.
    public boolean isOnline() {
        return online;
    }

    // Cambia el puerto de esucha del servidor
    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    // Cambia la direccion del servidor
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Inicia el servidor
     * 
     * @return Cuando el servidor se inicia correctamente o ya esta iniciado
     *         devuelve <strong>true</strong>. Cuando se produce un error y no se
     *         inicia el servidor se devuelve <strong>false</strong>.
     */
    public boolean start() {
        logger.info("Iniciando servidor ...");
        // Si el servidor ya esta iniciado no se hace nada
        if (isOnline()) {
            logger.info("No se hara nada porque el servidor ya estaba iniciado.");
            return true;
        }

        try {
            InetAddress addr = InetAddress.getByName(address);
            serverSocket = new ServerSocket(port, 0, addr);
            online = true;
            return true;
        } catch (IOException e) {
            logger.info("No se pudo inicar el servidor. " + e.getMessage());
        }

        // Error al iniciar el servidor
        return false;
    }

    /**
     * Detiene el servidor
     * 
     * @return Cuando el servidor se detiene correctamente o ya estaba detenido
     *         devuelve <strong>true</strong>. Cuando se produce un error y no se
     *         detiene el servidor se devuelve <strong>false</strong>.
     */
    public boolean stop() {
        logger.info("Deteniendo el servidor ...");

        // El servidor no esta iniciado.
        if (!isOnline()) {
            logger.info("No se hara nada, el servidor ya estaba offline.");
            return true;
        }

        try {
            serverSocket.close();
            online = false;
            return true;
        } catch (IOException e) {
            logger.info("No se pudo detener el servidor. " + e.getMessage());
        }
        return false;
    }
}

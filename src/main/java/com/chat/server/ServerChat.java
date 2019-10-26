package com.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.chat.network.SocketConnection;

/**
 * Esta clase implementa en el servidor metodos concretos para gestionar el
 * servidor del chat.
 * 
 * @author Damian
 */
public class ServerChat extends BasicServer {
    private static Logger logger = Logger.getLogger(ServerChat.class.getName());

    // Lista con las conexiones abiertas en el servidor
    private List<SocketConnection> clientConnections = new ArrayList<SocketConnection>();

    // private List<> authConnections; // Clientes logeados
    private int maxConnections = 100;

    public ServerChat() {
        setAddress(ServerApplication.config.getAddress());
        setPort(ServerApplication.config.getPort());
    }

    /**
     * Cierra todas las conexiones abiertas en el servidor
     */
    public void closeConnections() {
        logger.info("Se van a cerrar " + getCountConnections() + " conexiones.");
        for (SocketConnection serverConnection : clientConnections) {
            serverConnection.close();
        }
    }

    public void kick() {

    }

    /**
     * @return int Devuelve el numero de conexiones activas en el servidor
     */
    public int getCountConnections() {
        return clientConnections.size();
    }

    /**
     * Agrega una nueva conexion al servidor
     * 
     * @param connection
     * @return
     */
    public boolean addConnection(SocketConnection connection) {
        // Si hay demasiadas conexiones se rechaza
        if (getCountConnections() >= maxConnections) {
            logger.info("Limite de conexiones permitidas. Conexiones activas: " + getCountConnections() + " Limite:"
                    + maxConnections);
            return false;
        }

        logger.info("Agregando conexion");
        clientConnections.add(connection);
        return true;
    }

    @Override
    public boolean start() {
        if (!super.start()) {
            return false;
        }

        logger.info("Iniciando hilo");
        new Thread(this).start();

        ServerApplication.ui.log.append("Servidor escuchando en " + ServerApplication.config.getAddress() + ":"
                + ServerApplication.config.getPort());

        ServerApplication.ui.status.setAddress(getAddress());
        ServerApplication.ui.status.setPort(Integer.toString(getPort()));
        ServerApplication.ui.status.setStatus("ONLINE");
        return true;
    }

    @Override
    public boolean stop() {
        if (!super.stop()) {
            return false;
        }

        ServerApplication.ui.log.append("Servidor detenido.");
        ServerApplication.ui.status.setStatus("OFFLINE");
        logger.info("Servidor detenido");
        return true;
    }

    @Override
    public void run() {
        while (isOnline()) {
            try {
                Socket socket = serverSocket.accept();
                logger.info("Nuevo cliente conectado.");
                ServerApplication.ui.log.append("Un nuevo cliente se ha conectado.");
                ServerClientSocket client = new ServerClientSocket(socket);

                if (!addConnection(client)) {
                    client.close();
                }

            } catch (SocketException e) {
                logger.severe("El socket ha sido cerrado. " + e.getMessage());
            } catch (IOException e) {
                logger.severe("IO Error" + e.getMessage());
            }
        }
        logger.info("Loop exit. No se aceptaran mas clientes.");

        ServerApplication.ui.log.append("Cerrando conexiones.");
        closeConnections();
        clientConnections.clear();
        logger.info("Thread terminado.");
    }
}

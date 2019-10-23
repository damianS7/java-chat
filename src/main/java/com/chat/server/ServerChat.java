package com.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.chat.network.SocketConnection;

/**
 * Esta clase implementa en el servidor metodos concretos para gestionar el
 * servidor del chat.
 * 
 * @author Damian
 */
public class ServerChat extends BasicServer {
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

    public boolean addConnection(SocketConnection connection) {
        // Si hay demasiadas conexiones se rechaza
        if (getCountConnections() >= maxConnections) {
            return false;
        }

        clientConnections.add(connection);
        return true;
    }

    @Override
    public boolean start() {
        if (!super.start()) {
            return false;
        }

        new Thread(this).start();

        ServerApplication.ui.log.addLine("Servidor escuchando en " + ServerApplication.config.getAddress() + ":"
                + ServerApplication.config.getPort());

        ServerApplication.ui.setStatus("Started.");
        return true;
    }

    @Override
    public boolean stop() {
        if (!super.stop()) {
            return false;
        }

        ServerApplication.ui.log.addLine("Servidor detenido.");
        ServerApplication.ui.setStatus("Stopped.");
        return true;
    }

    @Override
    public void run() {
        while (isOnline()) {
            try {
                Socket socket = serverSocket.accept();
                ServerApplication.ui.log.addLine("Un nuevo cliente se ha conectado.");
                ServerClientSocket client = new ServerClientSocket(socket);

                if (!addConnection(client)) {
                    client.close();
                }

            } catch (SocketException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ServerApplication.ui.log.addLine("Cerrando conexiones.");
        closeConnections();
        clientConnections.clear();
    }
}

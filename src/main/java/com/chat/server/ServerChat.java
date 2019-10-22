package com.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa en el servidor metodos concretos para gestionar el
 * servidor del chat.
 * 
 * @author Damian
 */
public class ServerChat extends Server {
    private List<ServerConnection> connections = new ArrayList<ServerConnection>();
    private int maxConnections = 100;

    public ServerChat() {
	setAddress(ServerApplication.config.getAddress());
	setPort(ServerApplication.config.getPort());
    }

    public void closeConnections() {
	for (ServerConnection serverConnection : connections) {
	    serverConnection.close();
	}
    }

    public void kick() {

    }

    /**
     * @return int Devuelve el numero de conexiones activas en el servidor
     */
    public int getCountConnections() {
	return connections.size();
    }

    public boolean addConnection(ServerConnection connection) {
	// Si hay demasiadas conexiones se rechaza
	if (getCountConnections() >= maxConnections) {
	    connection.close();
	    return false;
	}

	connections.add(connection);
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
	while (isStarted()) {
	    try {
		Socket connectionSocket = serverSocket.accept();
		ServerConnection sc = new ServerConnection(connectionSocket);
		addConnection(sc);
		new Thread(sc).start();
	    } catch (SocketException e) {
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	ServerApplication.ui.log.addLine("Cerrando conexiones.");
	closeConnections();
	connections.clear();
    }
}

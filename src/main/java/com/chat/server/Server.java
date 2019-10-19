package com.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/*
 * Clase base para implementar el Servidor. 
 */
public abstract class Server implements Runnable {

    // Direccion donde el servidor aceptara los clientes
    private String address;
    
    // Puerto donde se escuchara
    private int port;
    
    // Socket principal del servidor
    private ServerSocket serverSocket;

    // Estado actual del servidor
    private boolean started = false;

    // Devuelve el estado actual del servidor. true para el servidor iniciado.
    public boolean isStarted() {
	return started;
    }
    
    // Cambia el puerto de esucha del servidor
    public void setPort(int port) {
	this.port = port;
    }
    
    // Cambia la direccion del servidor
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * Inicia el servidor
     * @return Cuando el servidor se inicia correctamente o ya esta iniciado
     * devuelve <strong>true</strong>. Cuando se produce un error y no se inicia el 
     * servidor se devuelve <strong>false</strong>.
     */
    public boolean start() {
	// Si el servidor ya esta iniciado no se hace nada
	if (isStarted()) {
	    return true;
	}

	try {
	    InetAddress addr = InetAddress.getByName(address);
	    serverSocket = new ServerSocket(port, 0, addr);
	    started = true;
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}


	// Error al iniciar el servidor
	return false;
    }

    /**
     * Detiene el servidor
     * @return Cuando el servidor se detiene correctamente o ya estaba detenido
     * devuelve <strong>true</strong>. Cuando se produce un error y no se detiene
     * el servidor se devuelve <strong>false</strong>.
     */
    public boolean stop() {
	// El servidor no esta iniciado.
	if (!isStarted()) {
	    return true;
	}

	try {
	    serverSocket.close();
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }
}

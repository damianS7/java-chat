package com.chat.server;

import com.chat.server.ui.ServerUI;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

/*
 * Esta clase es el "bootstrap" de la aplicacion. Desde esta clase tenemos
 * acceso a otros elementos de la aplicaccion como la UI, Logger o la
 * configuracion del servidor.
 * 
 * Tambien desde el metodo main se realizan comprobaciones para asegurar
 * que la aplicacion arranca con los requerimientos minimos para funcionar
 */
public class ServerApplication {
    public static ServerUI ui;
    public static ServerChat server;
    public static ServerPreferences config;

    public static void main(String[] args) {
	File file = new File("server.properties");
	
	// Creacion del fichero de configuracion en caso de no existir
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	
	config = new ServerPreferences(file);
	ServerApplication.server = new ServerChat();
	
        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServerApplication.ui = new ServerUI();
            }
        });
    }
}
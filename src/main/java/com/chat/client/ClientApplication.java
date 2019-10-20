package com.chat.client;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.chat.client.ui.ClientUI;
import com.chat.common.Preferences;

public class ClientApplication {
    public static ClientUI ui;
    public static ClientChat client;
    public static ClientPreferences config;
    public static Logger LOGGER = Logger.getLogger(ClientApplication.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Comprobando configuracion minima ...");

        // Comprobamos si existe el fichero de preferencias del cliente
        File filePreferences = new File("preferences.properties");

        if (!filePreferences.exists()) {
            LOGGER.log(Level.INFO,
                    "El fichero " + filePreferences.getName() + " no existe en " + filePreferences.getAbsolutePath());

            try {
                filePreferences.createNewFile();
                LOGGER.log(Level.INFO, filePreferences.getName() + " creado con exito.");
            } catch (SecurityException e) {
                LOGGER.log(Level.SEVERE, "No se puede crear el fichero " + filePreferences.getName()
                        + " porque no se tienen permisos de escritura en " + filePreferences.getAbsolutePath());
                // No se puede escribir en el directorio
            } catch (IOException e) {
                // Error IO
                e.printStackTrace();
            }
        }

        config = new ClientPreferences(filePreferences);

        client = new ClientChat();

        LOGGER.log(Level.INFO, "Todo OK. Iniciando cliente ...");

        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ui = new ClientUI();
            }
        });
    }
}

package com.chat.server;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.chat.server.database.AccountDatabase;
import com.chat.server.database.RoomDatabase;
import com.chat.server.ui.ServerUI;

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
    public static RoomDatabase roomDatabase;
    public static AccountDatabase accountDatabase;
    // public static Logger log

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        File fileProperties = new File("server.properties");
        File fileRooms = new File("rooms.sqlite");
        File fileAccounts = new File("accounts.sqlite");

        // Comprobacion de ficheros
        if (!fileProperties.exists()) {
            fileProperties.createNewFile();
        }

        if (!fileAccounts.exists()) {
            fileAccounts.createNewFile();
        }

        if (!fileRooms.exists()) {
            fileRooms.createNewFile();
        }

        roomDatabase = new RoomDatabase(fileRooms);
        roomDatabase.connect();
        roomDatabase.createStructure();

        accountDatabase = new AccountDatabase(fileAccounts);
        accountDatabase.connect();
        accountDatabase.createStructure();

        config = new ServerPreferences(fileProperties);
        server = new ServerChat();

        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServerApplication.ui = new ServerUI();
            }
        });
    }
}
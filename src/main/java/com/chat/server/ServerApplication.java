package com.chat.server;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger(ServerApplication.class.getName());
    public static AccountDatabase accountDatabase;
    public static RoomDatabase roomDatabase;
    public static ServerPreferences config;
    public static ServerChat server;
    public static ServerUI ui;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        logger.info("Iniciando servidor.");
        File fileProperties = new File("server.properties");
        File fileRooms = new File("rooms.sqlite");
        File fileAccounts = new File("accounts.sqlite");

        logger.info("Comprobando ficheros necesarios.");

        // Comprobacion de ficheros
        if (!fileProperties.exists()) {
            logger.info("Creando: " + fileProperties.getName());
            fileProperties.createNewFile();
        }

        if (!fileAccounts.exists()) {
            logger.info("Creando: " + fileAccounts.getName());
            fileAccounts.createNewFile();
        }

        if (!fileRooms.exists()) {
            logger.info("Creando: " + fileRooms.getName());
            fileRooms.createNewFile();
        }

        logger.info("Comprobando base de datos: " + fileRooms.getName());
        roomDatabase = new RoomDatabase(fileRooms);
        roomDatabase.connect();
        roomDatabase.createStructure();

        logger.info("Comprobando base de datos: " + fileAccounts.getName());
        accountDatabase = new AccountDatabase(fileAccounts);
        accountDatabase.connect();
        accountDatabase.createStructure();

        logger.info("Cargando preferencias");
        config = new ServerPreferences(fileProperties);

        logger.info("Iniciando ServerChat()");
        server = new ServerChat();

        logger.info("Iniciando UI.");
        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServerApplication.ui = new ServerUI();
            }
        });
    }
}
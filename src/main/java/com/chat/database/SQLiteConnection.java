package com.chat.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection implements DatabaseConnection {
    private Connection dbConnection;
    private File database;

    public SQLiteConnection(File database) {
        this.database = database;
    }

    // Devuelve la instancia para manejar la conexion
    public Connection getConnection() {
        return dbConnection;
    }

    // Comienza la conexion con la base de datos
    public void open() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager
                .getConnection("jdbc:sqlite:" + database.getPath());
    }

    // Cierra la conexion con la base de datos
    public void close() throws SQLException {
        dbConnection.close();
    }

    public String getDatabaseName() {
        return database.getPath();
    }

    public String getDatabasePath() {
        return database.getAbsolutePath();
    }

}
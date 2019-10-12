package com.chat.database;

import java.io.File;
import java.sql.SQLException;

public abstract class SQLiteDatabase {
    protected File databaseFile;
    protected SQLiteConnection connection;

    public SQLiteDatabase(File database) {
        this.databaseFile = database;
    }

    public String getPathToDB() {
        return connection.getDatabasePath();
    }

    public void connect() throws SQLException, ClassNotFoundException {
        connection = new SQLiteConnection(databaseFile);
        connection.open();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

}

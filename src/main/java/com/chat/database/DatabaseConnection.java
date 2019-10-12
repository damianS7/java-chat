package com.chat.database;

import java.sql.SQLException;

public interface DatabaseConnection {
    public void open() throws SQLException, ClassNotFoundException;

    public void close() throws SQLException;

}

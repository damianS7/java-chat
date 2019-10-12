package com.chat.server.database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chat.database.SQLiteDatabase;

public class RoomDatabase extends SQLiteDatabase {

    public RoomDatabase(File database) {
        super(database);
    }

    // Muestra los resultados de la base de datos
    public void print() throws SQLException {
        List<RoomDatabaseRow> udrList = getRooms();

        for (RoomDatabaseRow user : udrList) {
            System.out.println("Roomname: " + user.name + " Description: "
                    + user.description + " image path: " + user.imagePath);
        }
    }

    // Crea la tabla (si no existe) de la base datos
    public int createStructure() {
        String query = "CREATE TABLE IF NOT EXISTS ROOMS (NAME CHAR(20) "
                + "PRIMARY KEY NOT NULL, DESCRIPTION CHAR(50) NOT NULL, "
                + "IMAGE CHAR(10) NOT NULL)";
        return executeUpdate(query);
    }

    // Borra la tabla
    public int deleteStructure() {
        String query = "DROP TABLE ROOMS";
        return executeUpdate(query);
    }

    // Implementacion de truncate ya que en SQLite no existe.
    public void truncate() {
        deleteStructure();
        createStructure();
    }

    // 1 Si se agrega el usuario
    public int addRoom(RoomDatabaseRow room) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO ROOMS (NAME, DESCRIPTION, IMAGE) VALUES(?,?,?)";

        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, room.name);
        pstmt.setString(2, room.description);
        pstmt.setString(3, room.imagePath);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // Borra un usuario de la DB
    public int deleteRoom(String roomName) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM ROOMS WHERE NAME = ?";
        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, roomName);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // 1 Si se actualiza alguna fila
    // 0 Si no se actualiza ninguna fila
    public int updateRoom(String roomName, String field, String value)
            throws SQLException {
        int result = 0;
        String sql = "UPDATE ROOMS SET " + field + " = ? WHERE NAME = ?";
        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, value);
        pstmt.setString(2, roomName);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // Devuelve una lista con todos los usuarios de la DB
    public List<RoomDatabaseRow> getRooms() {
        List<RoomDatabaseRow> udrList = new ArrayList<RoomDatabaseRow>();
        try {
            Statement s = connection.getConnection().createStatement();
            ResultSet rs = s
                    .executeQuery("SELECT NAME, DESCRIPTION, IMAGE FROM ROOMS");
            while (rs.next()) {
                RoomDatabaseRow rdr = new RoomDatabaseRow();

                ResultSetMetaData rsm = rs.getMetaData();
                int numCols = rsm.getColumnCount();

                for (int indexColumn = 1; indexColumn <= numCols; indexColumn++) {
                    String key = rsm.getColumnName(indexColumn);
                    String value = rs.getString(indexColumn);

                    if (key.equals("NAME")) {
                        rdr.name = value;
                    }

                    if (key.equals("DESCRIPTION")) {
                        rdr.description = value;
                    }

                    if (key.equals("IMAGE")) {
                        rdr.imagePath = value;
                    }
                }

                udrList.add(rdr);
            }

            s.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return udrList;
    }

    // Ejecuta SQL del tipo INSERT, UPDATE y DELETE
    public int executeUpdate(String query) {
        int result = 0;
        try {
            Statement s = connection.getConnection().createStatement();
            result = s.executeUpdate(query);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Ejecuta SELECT
    public void executeQuery(String query) {
        try {
            Statement s = connection.getConnection().createStatement();
            s.executeQuery(query);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

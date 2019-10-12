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

public class UserDatabase extends SQLiteDatabase {

    public UserDatabase(File database) {
        super(database);
    }

    // Muestra los resultados de la base de datos
    public void print() throws SQLException {
        List<UserDatabaseRow> udrList = getUsers();

        for (UserDatabaseRow user : udrList) {
            System.out.println("Username: " + user.username + " Password: "
                    + user.password + " Role: " + user.role);
        }
    }

    // Crea la tabla (si no existe) de la base datos
    public int createStructure() {
        String query = "CREATE TABLE IF NOT EXISTS USERS (USERNAME CHAR(20) "
                + "PRIMARY KEY NOT NULL, PASSWORD CHAR(50) NOT NULL, "
                + "ROLE CHAR(10) NOT NULL)";
        return executeUpdate(query);
    }

    // Borra la tabla
    public int deleteStructure() {
        String query = "DROP TABLE USERS";
        return executeUpdate(query);
    }

    // Implementacion de truncate ya que en SQLite no existe.
    public void truncate() {
        deleteStructure();
        createStructure();
    }

    // 1 Si se agrega el usuario
    public int addUser(UserDatabaseRow user) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD,ROLE) VALUES(?,?,?)";

        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, user.username);
        pstmt.setString(2, user.password);
        pstmt.setString(3, user.role);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // Borra un usuario de la DB
    public int deleteUser(String username) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM USERS WHERE USERNAME = ?";
        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, username);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // 1 Si se actualiza alguna fila
    // 0 Si no se actualiza ninguna fila
    public int updateUser(String username, String field, String value)
            throws SQLException {
        int result = 0;
        String sql = "UPDATE USERS SET " + field + " = ? WHERE USERNAME = ?";
        PreparedStatement pstmt = connection.getConnection()
                .prepareStatement(sql);
        pstmt.setString(1, value);
        pstmt.setString(2, username);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // Devuelve una lista con todos los usuarios de la DB
    public List<UserDatabaseRow> getUsers() {
        List<UserDatabaseRow> udrList = new ArrayList<UserDatabaseRow>();
        try {
            Statement s = connection.getConnection().createStatement();
            ResultSet rs = s
                    .executeQuery("SELECT USERNAME, PASSWORD, ROLE FROM USERS");
            while (rs.next()) {
                UserDatabaseRow udr = new UserDatabaseRow();

                ResultSetMetaData rsm = rs.getMetaData();
                int numCols = rsm.getColumnCount();

                for (int indexColumn = 1; indexColumn <= numCols; indexColumn++) {
                    String key = rsm.getColumnName(indexColumn);
                    String value = rs.getString(indexColumn);

                    if (key.equals("USERNAME")) {
                        udr.username = value;
                    }

                    if (key.equals("PASSWORD")) {
                        udr.password = value;
                    }

                    if (key.equals("ROLE")) {
                        udr.role = value;
                    }
                }

                udrList.add(udr);
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

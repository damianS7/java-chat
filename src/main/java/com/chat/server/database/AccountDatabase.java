package com.chat.server.database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chat.common.BasicAccount;
import com.chat.database.SQLiteDatabase;

public class AccountDatabase extends SQLiteDatabase {

    public AccountDatabase(File database) {
        super(database);
    }

    // Muestra los resultados de la base de datos
    public void print() throws SQLException {
        List<BasicAccount> udrList = getAccounts();

        for (BasicAccount user : udrList) {
            System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword() + " Role: "
                    + user.getRole());
        }
    }

    // Crea la tabla (si no existe) de la base datos
    public int createStructure() {
        String query = "CREATE TABLE IF NOT EXISTS ACCOUNTS (USERNAME CHAR(20) "
                + "PRIMARY KEY NOT NULL, PASSWORD CHAR(50) NOT NULL, " + "ROLE CHAR(10) NOT NULL)";
        return executeUpdate(query);
    }

    // Borra la tabla
    public int deleteStructure() {
        String query = "DROP TABLE ACCOUNTS";
        return executeUpdate(query);
    }

    // Implementacion de truncate ya que en SQLite no existe.
    public void truncate() {
        deleteStructure();
        createStructure();
    }

    // 1 Si se agrega el usuario
    public int addAccount(BasicAccount account) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO ACCOUNTS (USERNAME, PASSWORD,ROLE) VALUES(?,?,?)";

        PreparedStatement pstmt = connection.getConnection().prepareStatement(sql);
        pstmt.setString(1, account.getUsername());
        pstmt.setString(2, account.getPassword());
        pstmt.setString(3, account.getRole().toString());
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // Borra un usuario de la DB
    public int deleteAccount(String username) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM ACCOUNTS WHERE USERNAME = ?";
        PreparedStatement pstmt = connection.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    // 1 Si se actualiza alguna fila
    // 0 Si no se actualiza ninguna fila
    public int updateAccount(String username, String field, String value) throws SQLException {
        int result = 0;
        String sql = "UPDATE ACCOUNTS SET " + field + " = ? WHERE USERNAME = ?";
        PreparedStatement pstmt = connection.getConnection().prepareStatement(sql);
        pstmt.setString(1, value);
        pstmt.setString(2, username);
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    /**
     * Busca una Account en la base de datos.
     * 
     * @param username - Nombre de la Account a buscar
     * @return Devuelve null si no encuentra una Account con el nombre indicado
     * @throws SQLException
     */
    public BasicAccount getAccount(String username) throws SQLException {
        String query = "SELECT USERNAME, PASSWORD, ROLE FROM ACCOUNTS WHERE USERNAME=?";
        PreparedStatement ps = connection.getConnection().prepareStatement(query);
        ps.setString(1, username);
        ps.setMaxRows(1);
        ResultSet rs = ps.executeQuery();

        // No existe la Account buscada
        if (!rs.next()) {
            return null;
        }

        BasicAccount account = new BasicAccount();
        account.setUsername(rs.getString("USERNAME"));
        account.setPassword(rs.getString("PASSWORD"));
        account.setRoleString(rs.getString("ROLE"));

        ps.close();
        rs.close();
        return account;
    }

    // Devuelve una lista con todos los usuarios de la DB
    public List<BasicAccount> getAccounts() {
        List<BasicAccount> accountList = new ArrayList<BasicAccount>();
        try {
            Statement s = connection.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT USERNAME, PASSWORD, ROLE FROM ACCOUNTS");
            while (rs.next()) {
                BasicAccount account = new BasicAccount();

                ResultSetMetaData rsm = rs.getMetaData();
                int numCols = rsm.getColumnCount();

                for (int indexColumn = 1; indexColumn <= numCols; indexColumn++) {
                    String key = rsm.getColumnName(indexColumn);
                    String value = rs.getString(indexColumn);

                    if (key.equals("USERNAME")) {
                        account.setUsername(value);
                    }

                    if (key.equals("PASSWORD")) {
                        account.setPassword(value);
                    }

                    if (key.equals("ROLE")) {
                        account.setRoleString(value);
                    }
                }

                accountList.add(account);
            }

            s.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
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

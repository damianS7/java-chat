package com.chat.client;

import java.io.File;

import com.chat.common.Preferences;


/*
 * Esta clase tiene acceso al fichero donde se almacena la informacion 
 * configurable del cliente. Actual de intermediario con el fichero permitiendo
 * recuperar o cambiar los valores almacenados.
 */
public class ClientPreferences extends Preferences {
    private String[][] fields = { { "username", "damianS7" }, { "password", "123456" }, { "server_ip", "127.0.0.1" },
            { "server_port", "7777" }, { "phonenumber", "123456789" }, { "save_logs", "false" },
            { "avatar_path", "resources/avatars/avatar1.jpg" } };

    public ClientPreferences(File file) {
	super(file);
	
	// Comprueba que las claves obligatorias estan en el fichero de
        // configuracion
        for (String v[] : fields) {
            // Clave no encontrada
            if (properties.getProperty(v[0]) == null) {
                setProperty(v[0], v[1]);
            }
        }

        save();
    }
    

    public String getAddress() {
        return properties.getProperty("server_ip");
    }

    public void setAddress(String address) {
        setProperty("server_ip", address);
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("server_port"));
    }

    public void setPort(int port) {
        setProperty("server_port", Integer.toString(port));
    }

    public void setPhone(String phone) {
        setProperty("phonenumber", phone);
    }

    public void setPassword(String password) {
        setProperty("password", password);
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getPhone() {
        return properties.getProperty("phonenumber");
    }

    public String getAvatarPath() {
        return properties.getProperty("avatar_path");
    }

    public void setAvatarPath(String path) {
        setProperty("avatar_path", path);
    }

    public boolean getSaveLogs() {
        return Boolean.parseBoolean(properties.getProperty("save_logs"));
    }

    public void setSaveLogs(String bool) {
        setProperty("save_logs", bool);
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public void setUsername(String username) {
        setProperty("username", username);
    }

}

package com.chat.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Preferences {
    public static Logger LOGGER = Logger.getLogger(Preferences.class.getName());
    private Properties properties;
    private File file;
    private String[][] fields = { { "username", "damianS7" }, { "password", "123456" }, { "server_ip", "127.0.0.1" },
            { "server_port", "7777" }, { "phonenumber", "123456789" }, { "save_logs", "false" },
            { "avatar_path", "resources/avatars/avatar1.jpg" } };

    public Preferences(File file) {
        this.file = file;
        properties = new Properties();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (SecurityException e) {
            // Sin permisos de lectura
            LOGGER.log(Level.SEVERE, "No se puede leer el archivo " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            // Fichero no encontrado, generar nuevo
            LOGGER.log(Level.SEVERE, "El archivo no existe en la ruta especificada " + file.getAbsolutePath());
        }

        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    // Guarda en un fichero
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, "Chat client config");
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "No se pudo guardar las preferencias porque el archivo no existe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.log(Level.INFO, "Archivo de preferencias actualizado.");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void print() {
        for (String v[] : fields) {
            System.out.println("Key -> " + v[0] + ":" + properties.getProperty(v[0]));
        }
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

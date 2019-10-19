package com.chat.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Preferences {
    public static Logger LOGGER = Logger.getLogger(Preferences.class.getName());
    protected Properties properties;
    private File file;

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
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void print() {
        for (Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }

}

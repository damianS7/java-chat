package com.chat.server;

import java.io.File;

import com.chat.common.Preferences;

/*
 * Clase que gestiona las opciones configurables en el servidor, estas opciones
 * se guardan en un archivo de texto.
 */
public class ServerPreferences extends Preferences {

    // Array con las opciones obligatorias del servidor.
    private String[][] requiredFields = { { "address", "127.0.0.1" }, { "port", "7777" }, { "save_logs", "false" } };

    public ServerPreferences(File file) {
        super(file);

        // Comprobamos que los campos requeridos existan el fichero
        for (String objectArray[] : requiredFields) {
            // No se encuentra el campo en el fichero, se agrega con setProperty
            if (properties.getProperty(objectArray[0]) == null) {
                setProperty(objectArray[0], objectArray[1]);
            }
        }

        // Guarda los cambios en el fichero de configuracion
        save();
    }

    // Configura una nueva direccion para el servidor
    public void setAddress(String address) {
        setProperty("address", address);
    }

    // Obtiene la direccion que el servidor usa para escuchar
    public String getAddress() {
        return getProperty("address");
    }

    // Configura un nuevo puerto para el servidor
    public void setPort(String port) {
        setProperty("port", port);
    }

    // Obtiene el puerto utilizado para escuchar
    public int getPort() {
        return Integer.parseInt(getProperty("port"));
    }
}

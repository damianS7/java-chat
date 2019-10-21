package com.chat.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa en el servidor metodos concretos para gestionar el
 * servidor del chat.
 * 
 * @author Damian
 */
public class ServerChat extends Server {
    private List<ServerConnection> connections = new ArrayList<ServerConnection>();

    public ServerChat() {
        setAddress(ServerApplication.config.getAddress());
        setPort(ServerApplication.config.getPort());
    }

    @Override
    public boolean start() {
        if (!super.start()) {
            return false;
        }

        ServerApplication.ui.log.addLine("Servidor escuchando en " + ServerApplication.config.getAddress() + ":"
                + ServerApplication.config.getPort());

        ServerApplication.ui.setStatus("Started.");
        return true;
    }

    @Override
    public boolean stop() {
        if (!super.stop()) {
            return false;
        }

        ServerApplication.ui.log.addLine("Servidor detenido.");

        ServerApplication.ui.setStatus("Stopped.");
        return true;
    }

    public void kick() {

    }

    @Override
    public void run() {

    }
}

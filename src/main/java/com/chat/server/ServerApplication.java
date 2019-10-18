package com.chat.server;

import com.chat.server.ui.ServerUI;
import java.awt.EventQueue;

public class ServerApplication {
    public static ServerUI ui;

    public static void main(String[] args) {
        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ui = new ServerUI();
            }
        });
    }
}
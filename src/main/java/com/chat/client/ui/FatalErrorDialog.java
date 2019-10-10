package com.chat.client.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FatalErrorDialog {

    public FatalErrorDialog(String message) {
        this("Error Fatal", message);
    }

    public FatalErrorDialog(String title, String message) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.WARNING_MESSAGE);
        System.exit(1);
    }

}

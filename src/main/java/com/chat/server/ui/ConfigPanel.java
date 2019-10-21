package com.chat.server.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/*
 * Panel que contiene la configuracion de las opciones modificables del
 * servidor.
 */

public class ConfigPanel extends JPanel {
    private JTextField textField;
    private JTextField textField_1;

    public ConfigPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new MigLayout("", "[grow,right][grow,right]", "[][][grow][]"));

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress, "cell 0 0,alignx trailing");

        textField = new JTextField();
        panel.add(textField, "cell 1 0,growx");
        textField.setColumns(10);

        JLabel lblPort = new JLabel("Port");
        panel.add(lblPort, "cell 0 1,alignx trailing");

        textField_1 = new JTextField();
        panel.add(textField_1, "cell 1 1,growx");
        textField_1.setColumns(10);

        JButton btnSave = new JButton("SAVE");
        panel.add(btnSave, "cell 1 3");
    }

}

package com.chat.server.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.chat.server.ServerApplication;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/*
 * Panel que contiene la configuracion de las opciones modificables del
 * servidor.
 */

public class ConfigPanel extends JPanel {
    private JTextField addressField;
    private JTextField portField;

    public ConfigPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new MigLayout("", "[grow,right][grow,fill]", "[][][grow][]"));

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress, "cell 0 0,alignx trailing");

        addressField = new JTextField(ServerApplication.config.getAddress());
        addressField.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(addressField, "cell 1 0,growx");
        addressField.setColumns(10);

        JLabel lblPort = new JLabel("Port");
        panel.add(lblPort, "cell 0 1,alignx trailing");

        portField = new JTextField(Integer.toString(ServerApplication.config.getPort()));
        portField.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(portField, "cell 1 1,alignx right");
        portField.setColumns(10);

        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerApplication.config.setAddress(addressField.getText());
                ServerApplication.config.setPort(portField.getText());
                ServerApplication.config.save();
            }
        });
        panel.add(btnSave, "cell 1 3");
    }

}

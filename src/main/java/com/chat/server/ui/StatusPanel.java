package com.chat.server.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class StatusPanel extends JPanel {
    private static final long serialVersionUID = 6412640342244025810L;
    private JLabel statusValue;
    private JLabel clientValue;
    private JLabel portValue;
    private JLabel addressValue;

    public StatusPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new MigLayout("", "[grow,right][grow,right]", "[][][][][][][][grow][]"));

        JLabel labelAddress = new JLabel("Address");
        panel.add(labelAddress, "cell 0 0,alignx trailing");

        addressValue = new JLabel("...");
        panel.add(addressValue, "cell 1 0");

        JLabel labelPort = new JLabel("Port");
        panel.add(labelPort, "cell 0 1,alignx trailing");

        portValue = new JLabel("...");
        panel.add(portValue, "cell 1 1");

        JLabel labelStatus = new JLabel("Status");
        panel.add(labelStatus, "cell 0 2");

        statusValue = new JLabel("");
        setStatus("OFFLINE");
        panel.add(statusValue, "cell 1 2");

        JLabel labelClients = new JLabel("Users online");
        panel.add(labelClients, "cell 0 3");

        clientValue = new JLabel("0");
        panel.add(clientValue, "cell 1 3");

        JLabel lblAccounts = new JLabel("Loaded Accounts");
        panel.add(lblAccounts, "cell 0 4");

        JLabel label = new JLabel("0");
        panel.add(label, "cell 1 4");

        JLabel lblLoadedRooms = new JLabel("Loaded rooms");
        panel.add(lblLoadedRooms, "cell 0 5");

        JLabel lblNewLabel = new JLabel("0");
        panel.add(lblNewLabel, "cell 1 5");

        JLabel lblUptime = new JLabel("Uptime");
        panel.add(lblUptime, "cell 0 6");

        JLabel lblNewLabel_1 = new JLabel("0");
        panel.add(lblNewLabel_1, "cell 1 6");
    }

    public void setStatus(String status) {
        Color color = Color.RED;

        if (status.equalsIgnoreCase("ONLINE")) {
            color = Color.GREEN;
        }

        statusValue.setForeground(color);
        statusValue.setText(status);
    }

    public void setConnected(String connected) {
        clientValue.setText(connected);
    }

    public void setAddress(String address) {
        addressValue.setText(address);
    }

    public void setPort(String port) {
        portValue.setText(port);
    }

}

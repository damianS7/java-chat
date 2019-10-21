package com.chat.server.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class HomePanel extends JPanel {

    public HomePanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new MigLayout("", "[grow,right][grow,right]", "[][][][][grow][]"));

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress, "cell 0 0,alignx trailing");
        
        JLabel label = new JLabel("0");
        panel.add(label, "cell 1 0");


        JLabel lblPort = new JLabel("Port");
        panel.add(lblPort, "cell 0 1,alignx trailing");
        
        JLabel label_1 = new JLabel("0");
        panel.add(label_1, "cell 1 1");
        
        JLabel lblStatus = new JLabel("Status");
        panel.add(lblStatus, "cell 0 2");
        
        JLabel label_2 = new JLabel("0");
        panel.add(label_2, "cell 1 2");
        
        JLabel lblClients = new JLabel("Clients");
        panel.add(lblClients, "cell 0 3");
        
        JLabel label_3 = new JLabel("0");
        panel.add(label_3, "cell 1 3");
    }
}

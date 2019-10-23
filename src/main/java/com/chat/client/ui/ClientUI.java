package com.chat.client.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chat.client.ClientApplication;

import net.miginfocom.swing.MigLayout;

public class ClientUI {
    public JFrame frame;
    public JMenuBar menuBar;
    private LogTab log;
    private JTabbedPane tabsPanel;
    private JListRooms rooms;

    // Array con los tabs abiertos en el cliente (Conversaciones/salas de chat
    public List<Tab> tabs = new ArrayList<Tab>();
    private JPanel panel;
    private JButton btnConnect;
    private JLabel lblStatus;
    private JLabel status;

    public ClientUI() {
        setSystemLookAndFeel();
        initComponents();

        addTab(log);

        panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        frame.getContentPane().add(panel, "cell 0 1 2 1,grow");

        btnConnect = new JButton("Connect");
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClientApplication.client.isConnected()) {
                    ClientApplication.client.disconnect();
                } else {
                    ClientApplication.client.connect();
                }
            }
        });

        lblStatus = new JLabel("Status:");
        panel.add(lblStatus);

        status = new JLabel("Disconnected");
        status.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                // Color para online
                if (status.getText().equals("Connected")) {
                    status.setForeground(Color.GREEN);
                    btnConnect.setText("Disconnect");
                } else {
                    // Color para el resto de estados
                    status.setForeground(Color.RED);
                    btnConnect.setText("Connect");
                }
            }
        });
        panel.add(status);
        panel.add(btnConnect);
        frame.setVisible(true);
    }

    /**
     * Obtiene un tab de la lista
     * 
     * @param title El titulo del tab que se desea buscar
     * @return una instancia de TabChat o null si no es encontrado
     */
    public Tab getTab(String title) {
        for (Tab tabChat : tabs) {
            if (tabChat.getTitle().equals(title)) {
                return tabChat;
            }
        }
        return null;
    }

    // Añade un tab a la lista y al tabbedPane
    public void addTab(Tab tab) {

        // Ya existe un tab con este nombre
        if (getTab(tab.getTitle()) != null) {
            tabsPanel.setSelectedComponent(getTab(tab.getTitle()));
            return;
        }

        tabs.add(tab);
        tabsPanel.add(tab.getTitle(), tab);
        tabsPanel.setSelectedComponent(tab);
    }

    // Borra un "tab" de la lista "tabs" y del tabbedPane
    public void removeTab(TabChat tab) {
        tabs.remove(tab);
        tabsPanel.remove(tab);
    }

    private void setSystemLookAndFeel() {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initComponents() {
        frame = new JFrame();
        menuBar = new MenuBar();
        rooms = new JListRooms();
        log = new LogTab();

        frame.setSize(900, 600);
        frame.setTitle("Chat - Client");
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        MigLayout layout = new MigLayout("", "[100px:n][grow]", "[grow][]");
        frame.getContentPane().setLayout(layout);

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, "cell 0 0,grow");

        scrollPane.setViewportView(rooms);

        tabsPanel = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabsPanel, "cell 1 0,grow");
    }

    public void setStatus(String status) {
        lblStatus.setText(status);
    }

}

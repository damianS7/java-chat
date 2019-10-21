package com.chat.server.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chat.server.ServerApplication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/*
 * Interfaz grafica principal del servidor. Tambien contiene metodos
 * que permiten la interaccion con otros elementos graficos de la UI.
 */
public class ServerUI {
    private JPanel panelLoader;
    public JFrame frame;
    public LogPanel log;
    public ConfigPanel config;
    private JLabel lblTitle;

    public ServerUI() {
        setLookAndFeel();
        initComponents();
        JPanel panelMenu = new JPanel();
        panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMenu.setBackground(new Color(55, 66, 250));
        panelMenu.setBounds(0, 0, 180, 400);
        frame.getContentPane().add(panelMenu);
        panelMenu.setLayout(new MigLayout("", "[16.00][grow,left]", "[][][][][][][][][][][][grow][][]"));

        JLabel lblAdministration = new JLabel("Administration");
        lblAdministration.setForeground(new Color(185, 187, 189));
        lblAdministration.setFont(new Font("Noto Sans", Font.BOLD, 16));
        panelMenu.add(lblAdministration, "cell 0 0 2 1,alignx center");

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon(ServerUI.class.getResource("/images/home.png")));
        panelMenu.add(label_1, "cell 0 1");

        JButton btnHome = new JButtonLink("Home");
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Home", new HomePanel());                
            }
        });
        panelMenu.add(btnHome, "cell 1 1,alignx left");

        JLabel label_2 = new JLabel("");
        label_2.setIcon(new ImageIcon(ServerUI.class.getResource("/images/rooms.png")));
        panelMenu.add(label_2, "cell 0 2");

        JButton btnRooms = new JButtonLink("Rooms");
        btnRooms.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Rooms", new RoomsPanel());
            }
        });
        panelMenu.add(btnRooms, "cell 1 2");

        JLabel labelAccounts = new JLabel("");
        labelAccounts.setIcon(new ImageIcon(ServerUI.class.getResource("/images/accounts.png")));
        panelMenu.add(labelAccounts, "cell 0 3");

        JButton btnAccounts = new JButtonLink("Accounts");
        btnAccounts.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Accounts", new AccountsPanel());
            }
        });
        panelMenu.add(btnAccounts, "cell 1 3");

        JLabel label_4 = new JLabel("");
        label_4.setIcon(new ImageIcon(ServerUI.class.getResource("/images/privileges.png")));
        panelMenu.add(label_4, "cell 0 4");

        JButton btnPrivileges = new JButtonLink("Privileges");
        btnPrivileges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Privileges", new PrivilegesPanel());
            }
        });
        panelMenu.add(btnPrivileges, "cell 1 4");

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(ServerUI.class.getResource("/images/config.png")));
        panelMenu.add(label, "cell 0 5");

        JButton btnLog = new JButtonLink("Log");
        btnLog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Server Log", log);
            }
        });

        JButton btnConfig = new JButtonLink("Config");
        btnConfig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Server config", config);
            }
        });
        panelMenu.add(btnConfig, "cell 1 5");

        JLabel label_5 = new JLabel("");
        label_5.setIcon(new ImageIcon(ServerUI.class.getResource("/images/log.png")));
        panelMenu.add(label_5, "cell 0 6");
        panelMenu.add(btnLog, "cell 1 6");

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ServerApplication.server.isStarted()) {
                    ServerApplication.server.start();
                    btnStart.setText("STOP");
                } else {
                    ServerApplication.server.stop();
                    btnStart.setText("START");
                }
            }
        });
        panelMenu.add(btnStart, "cell 0 13 2 1,growx");

        JPanel titlePanel = new TitlePanel();
        titlePanel.setBounds(180, 0, 720, 30);
        frame.getContentPane().add(titlePanel);
        titlePanel.setLayout(null);

        lblTitle = new JLabel("");
        lblTitle.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblTitle.setBounds(15, 4, 464, 25);
        titlePanel.add(lblTitle);

        JButton closeButton = new JButtonLink("");
        closeButton.setIcon(new ImageIcon(ServerUI.class.getResource("/images/close.png")));
        closeButton.setFont(new Font("Noto Sans", Font.BOLD, 18));
        closeButton.setForeground(new Color(255, 0, 0));
        closeButton.setBounds(694, 4, 26, 14);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        titlePanel.add(closeButton);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(4, 28, 690, 4);
        titlePanel.add(separator);

        panelLoader = new JPanel();
        panelLoader.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelLoader.setBounds(180, 30, 720, 370);
        frame.getContentPane().add(panelLoader);
        panelLoader.setLayout(new BorderLayout(0, 0));
        
        loadPanel("Home", new HomePanel());
        frame.setVisible(true);
    }

    private void setLookAndFeel() {
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
    
    public void initComponents() {
        frame = new JFrame();
        log = new LogPanel();
        config = new ConfigPanel();

        frame.setUndecorated(true);
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
    }

    // Cambia el texto para mostrar estado actual del servidor
    public void setStatus(String status) {
        // labelStatus.setText(status);
    }

    // Actualiza el numero de conectados en la UI
    public void setConnected(String connected) {
        // labelConnected.setText(connected);
    }

    // Carga un panel en la interfaz
    public void loadPanel(String title, JPanel panel) {
        if (panelLoader.getComponentCount() > 0) {
            panelLoader.removeAll();
        }

        lblTitle.setText(title);
        panelLoader.add(panel, BorderLayout.CENTER);
        panelLoader.updateUI();
    }
}

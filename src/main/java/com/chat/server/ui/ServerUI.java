package com.chat.server.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.chat.server.ServerApplication;

import net.miginfocom.swing.MigLayout;

/*
 * Interfaz grafica principal del servidor. Tambien contiene metodos
 * que permiten la interaccion con otros elementos graficos de la UI.
 */
public class ServerUI {
    private JPanel panelLoader;
    private JLabel lblTitle;
    private JFrame frame;
    public StatusPanel status;
    public LogPanel log;
    public ConfigPanel config;
    private Font openSans;
    private Font menuFont;
    private Font titleFont;
    private Font menuLinkFont;

    public ServerUI() {
        setLookAndFeel();
        initComponents();
        JPanel panelMenu = new JPanel();
        panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMenu.setBackground(new Color(55, 66, 250));
        panelMenu.setBounds(0, 0, 180, 400);
        frame.getContentPane().add(panelMenu);
        panelMenu.setLayout(new MigLayout("", "[16.00][grow,left]", "[][][][][][][][][][][][grow][][]"));

        JLabel lblAdministration = new JLabel("Java Chat");
        lblAdministration.setFont(menuFont);
        lblAdministration.setForeground(new Color(185, 187, 189));
        panelMenu.add(lblAdministration, "cell 0 0 2 1,alignx center");

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon(ServerUI.class.getResource("/images/home.png")));
        panelMenu.add(label_1, "cell 0 1");

        JButton btnHome = new JButtonLink("Status");
        btnHome.setFont(menuLinkFont);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPanel("Server status", new StatusPanel());
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

        JButton btnStart = new JButton("START");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ServerApplication.server.isOnline()) {
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
        lblTitle.setFont(titleFont);
        lblTitle.setBounds(15, 4, 464, 25);
        titlePanel.add(lblTitle);

        JButton closeButton = new JButtonLink("");
        closeButton.setIcon(new ImageIcon(ServerUI.class.getResource("/images/close.png")));
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

        status = new StatusPanel();
        loadPanel("Server status", status);
        frame.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("awt.aatext", "true");

            openSans = Font.createFont(Font.TRUETYPE_FONT,
                    ServerUI.class.getResource("/fonts/OpenSans-Regular.ttf").openStream());
            openSans = openSans.deriveFont(13f);

            menuFont = openSans.deriveFont(Font.BOLD, 16f);
            menuLinkFont = openSans.deriveFont(14f);
            titleFont = openSans.deriveFont(Font.BOLD, 18f);

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Label.font", openSans);
            UIManager.put("Button.font", openSans);
            UIManager.put("List.font", openSans);
            UIManager.put("TextField.font", openSans);
            UIManager.put("ComboBox.font", openSans);
            UIManager.put("TextArea.font", openSans);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

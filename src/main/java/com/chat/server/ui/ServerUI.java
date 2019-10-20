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

/*
 * Interfaz grafica principal del servidor. Tambien contiene metodos
 * que permiten la interaccion con otros elementos graficos de la UI.
 */
public class ServerUI {
    private JPanel panelLoader;
    private JLabel labelConnected;
    private JLabel labelStatus;
    public JFrame frame;
    public LogPanel log;
    public ConfigPanel config;

    public ServerUI() {
        initComponents();
        
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(242, 243, 245));
        panelMenu.setBounds(0, 0, 180, 400);
        frame.getContentPane().add(panelMenu);
        panelMenu.setLayout(new MigLayout("", "[16.00][grow,left]", "[][][][][][][][][]"));
        
        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        menuButton.setBackground(null);
        menuButton.setBorder(null);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});
        
        JLabel lblAdministration = new JLabel("Administration");
        panelMenu.add(lblAdministration, "cell 0 0 2 1,alignx center");
        
        JButton btnInfo = new JButton("Info");
        panelMenu.add(btnInfo, "cell 1 1,alignx left");
        
        JButton btnRooms = new JButton("Rooms");
        panelMenu.add(btnRooms, "cell 1 2");
        
        JButton btnAccounts = new JButton("Accounts");
        panelMenu.add(btnAccounts, "cell 1 3");
        
        JButton btnPrivileges = new JButton("Privileges");
        panelMenu.add(btnPrivileges, "cell 1 4");
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(ServerUI.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
        panelMenu.add(label, "cell 0 5");
        
        panelMenu.add(menuButton, "cell 1 5");
        
        JButton btnLog = new JButton("Log");
        btnLog.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel(log);
	    }
	});
        panelMenu.add(btnLog, "cell 1 6");
        
        JButton btnConfig = new JButton("Config");
        btnConfig.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel(config);
	    }
	});
        panelMenu.add(btnConfig, "cell 1 7");
        
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if ( !ServerApplication.server.isStarted() ) {
		    ServerApplication.server.start();
		    btnStart.setText("STOP");
		} else {
		    ServerApplication.server.stop();
		    btnStart.setText("START");
		}
	    }
	});
        panelMenu.add(btnStart, "cell 1 8");
        
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(51, 153, 255));
        panelInfo.setBounds(180, 0, 720, 30);
        frame.getContentPane().add(panelInfo);
        panelInfo.setLayout(null);
        
        JLabel lblJavaChat = new JLabel("Java Chat");
        lblJavaChat.setFont(new Font("Ubuntu", Font.BOLD, 22));
        lblJavaChat.setBounds(15, 10, 115, 19);
        panelInfo.add(lblJavaChat);
        
        JButton closeButton = new JButton("X");
        closeButton.setBounds(629, 0, 91, 29);
        closeButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});
        panelInfo.add(closeButton);
        
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(134, 11, 69, 20);
        panelInfo.add(lblStatus);
        
        JLabel lblClients = new JLabel("Clients");
        lblClients.setBounds(284, 11, 69, 20);
        panelInfo.add(lblClients);
        
        JButton btnD = new JButton("D");
        btnD.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		showDecotarions();
	    }
	});
        btnD.setBounds(498, 7, 115, 23);
        panelInfo.add(btnD);
        
        labelStatus = new JLabel("Disconnected");
        labelStatus.setBounds(190, 11, 69, 20);
        panelInfo.add(labelStatus);
        
        labelConnected = new JLabel("0");
        labelConnected.setBounds(385, 11, 69, 20);
        panelInfo.add(labelConnected);
        
        panelLoader = new JPanel();
        panelLoader.setBackground(new Color(51, 255, 204));
        panelLoader.setBounds(180, 30, 720, 370);
        frame.getContentPane().add(panelLoader);
        panelLoader.setLayout(new BorderLayout(0, 0));
        frame.setVisible(true);
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
	labelStatus.setText(status);
    }
    
    // Actualiza el numero de conectados en la UI
    public void setConnected(String connected) {
	labelConnected.setText(connected);
    }
    
    // Muestra u oculta las decoraciones de ventana
    public void showDecotarions() {
	frame.dispose();
	if (frame.isUndecorated()) {
	    frame.setUndecorated(false);
	} else {
	    frame.setUndecorated(true);
	}
	frame.setVisible(true);
    }
    
    // Carga un panel en la interfaz
    public void loadPanel(JPanel panel) {
	if ( panelLoader.getComponentCount() > 0) {
	    panelLoader.removeAll();
	}
	
	panelLoader.add(panel, BorderLayout.CENTER);
	panelLoader.updateUI();
    }
}

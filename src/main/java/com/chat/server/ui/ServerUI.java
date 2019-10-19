package com.chat.server.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chat.server.ServerApplication;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

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
        panelMenu.setBackground(new Color(0, 0, 255));
        panelMenu.setBounds(0, 0, 100, 400);
        frame.getContentPane().add(panelMenu);
        panelMenu.setLayout(new MigLayout("", "[grow]", "[][][][]"));
        
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});
        
        panelMenu.add(menuButton, "cell 0 0");
        
        JButton btnLog = new JButton("Log");
        btnLog.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel(log);
	    }
	});
        panelMenu.add(btnLog, "cell 0 1");
        
        JButton btnConfig = new JButton("Config");
        btnConfig.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel(config);
	    }
	});
        panelMenu.add(btnConfig, "cell 0 2");
        
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
        panelMenu.add(btnStart, "cell 0 3");
        
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(51, 153, 255));
        panelInfo.setBounds(100, 0, 700, 100);
        frame.getContentPane().add(panelInfo);
        panelInfo.setLayout(null);
        
        JLabel lblJavaChat = new JLabel("Java Chat");
        lblJavaChat.setFont(new Font("Ubuntu", Font.BOLD, 48));
        lblJavaChat.setBounds(7, 11, 254, 73);
        panelInfo.add(lblJavaChat);
        
        JButton closeButton = new JButton("X");
        closeButton.setBounds(629, 7, 64, 29);
        closeButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});
        panelInfo.add(closeButton);
        
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(292, 11, 69, 20);
        panelInfo.add(lblStatus);
        
        JLabel lblClients = new JLabel("Clients");
        lblClients.setBounds(292, 47, 69, 20);
        panelInfo.add(lblClients);
        
        JButton btnD = new JButton("D");
        btnD.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		showDecotarions();
	    }
	});
        btnD.setBounds(498, 7, 115, 29);
        panelInfo.add(btnD);
        
        labelStatus = new JLabel("Disconnected");
        labelStatus.setBounds(377, 11, 69, 20);
        panelInfo.add(labelStatus);
        
        labelConnected = new JLabel("0");
        labelConnected.setBounds(387, 49, 69, 20);
        panelInfo.add(labelConnected);
        
        panelLoader = new JPanel();
        panelLoader.setBackground(new Color(51, 255, 204));
        panelLoader.setBounds(100, 100, 700, 300);
        frame.getContentPane().add(panelLoader);
        panelLoader.setLayout(new BorderLayout(0, 0));
        frame.setVisible(true);
    }
    
    public void initComponents() {
	frame = new JFrame();
        log = new LogPanel();
        config = new ConfigPanel();
        
        frame.setUndecorated(true);
        frame.setSize(800, 400);
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

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
        initComponents();
        
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(242, 243, 245));
        panelMenu.setBounds(0, 0, 180, 400);
        frame.getContentPane().add(panelMenu);
        panelMenu.setLayout(new MigLayout("", "[16.00][grow,left]", "[][][][][][][][][][][][grow][][]"));
        
        JLabel lblAdministration = new JLabel("Administration");
        panelMenu.add(lblAdministration, "cell 0 0 2 1,alignx center");
        
        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon(ServerUI.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
        panelMenu.add(label_1, "cell 0 1");
        
        JButton btnInfo = new JButtonLink("Info");
        panelMenu.add(btnInfo, "cell 1 1,alignx left");
        
        JLabel label_2 = new JLabel("");
        panelMenu.add(label_2, "cell 0 2");
        
        JButton btnRooms = new JButtonLink("Rooms");
        panelMenu.add(btnRooms, "cell 1 2");
        
        JLabel label_3 = new JLabel("");
        panelMenu.add(label_3, "cell 0 3");
        
        JButton btnAccounts = new JButtonLink("Accounts");
        panelMenu.add(btnAccounts, "cell 1 3");
        
        JLabel label_4 = new JLabel("");
        panelMenu.add(label_4, "cell 0 4");
        
        JButton btnPrivileges = new JButtonLink("Privileges");
        panelMenu.add(btnPrivileges, "cell 1 4");
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(ServerUI.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
        panelMenu.add(label, "cell 0 5");
        
        JButton btnLog = new JButtonLink("Log");
        btnLog.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel("Server Log", log);
	    }
	});
        
        JLabel label_5 = new JLabel("");
        panelMenu.add(label_5, "cell 0 6");
        panelMenu.add(btnLog, "cell 1 6");
        
        JButton btnConfig = new JButtonLink("Config");
        btnConfig.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		loadPanel("Server config", config);
	    }
	});
        
        JLabel label_6 = new JLabel("");
        panelMenu.add(label_6, "cell 0 7");
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
        panelMenu.add(btnStart, "cell 0 13 2 1,growx");
        
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(51, 153, 255));
        panelInfo.setBounds(180, 0, 720, 30);
        frame.getContentPane().add(panelInfo);
        panelInfo.setLayout(null);
        
        lblTitle = new JLabel("Java Chat");
        lblTitle.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblTitle.setBounds(15, 4, 104, 25);
        panelInfo.add(lblTitle);
        
        JButton closeButton = new JButtonLink("X");
        closeButton.setFont(new Font("Noto Sans", Font.BOLD, 18));
        closeButton.setForeground(new Color(255, 0, 0));
        closeButton.setBounds(698, 0, 22, 20);
        closeButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});
        panelInfo.add(closeButton);
        
        panelLoader = new JPanel();
        panelLoader.setBackground(new Color(0, 0, 128));
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
    
    // Carga un panel en la interfaz
    public void loadPanel(String title, JPanel panel) {
	if ( panelLoader.getComponentCount() > 0) {
	    panelLoader.removeAll();
	}
	
	lblTitle.setText(title);
	panelLoader.add(panel, BorderLayout.CENTER);
	panelLoader.updateUI();
    }
}

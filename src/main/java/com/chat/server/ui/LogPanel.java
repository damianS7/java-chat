package com.chat.server.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

/*
 * Interfaz grafica formada por un JPanel que muestra los eventos 
 * mas importantes que se registran en el servidor.
 */
public class LogPanel extends JPanel {
    private JTextArea textArea;

    public LogPanel() {
	setLayout(new BorderLayout(0, 0));

	textArea = new JTextArea();
	textArea.setEditable(false);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	add(scrollPane, BorderLayout.CENTER);
	scrollPane.setViewportView(textArea);
	
	JPanel panel = new JPanel();
	scrollPane.setColumnHeaderView(panel);
	panel.setLayout(new BorderLayout(0, 0));
	
	JPanel panel_1 = new JPanel();
	panel.add(panel_1);
	panel_1.setLayout(new BorderLayout(0, 0));
	
	JLabel lblServerLogs = new JLabel("Server logs");
	panel_1.add(lblServerLogs);
	lblServerLogs.setFont(new Font("Ubuntu", Font.BOLD, 32));
	
	JSeparator separator = new JSeparator();
	panel_1.add(separator, BorderLayout.SOUTH);
    }
    
    // Agrega una nueva linea al textarea que contiene el log.
    public void addLine(String line) {
	textArea.append(line + "\n");
	textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    // Borrar por completo el log mostrado.
    public void clear() {
	textArea.setText("");
    }
}

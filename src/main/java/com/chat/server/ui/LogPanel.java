package com.chat.server.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

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
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(textArea);
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

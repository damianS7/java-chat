package com.chat.server.ui;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class JButtonLink extends JButton {

    public JButtonLink(String text) {
	super(text);
	setFont(new Font("Ubuntu", Font.PLAIN, 18));
        setBackground(null);
        setBorder(null);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

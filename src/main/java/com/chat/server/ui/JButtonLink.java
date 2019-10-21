package com.chat.server.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class JButtonLink extends JButton {

    public JButtonLink(String text) {
        super(text);
        setFont(new Font("Noto Sans", Font.PLAIN, 14));
        setForeground(new Color(185, 187, 189));
        setBackground(null);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

package com.chat.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private JMenu file;
    private JMenu edit;
    private JMenu help;

    public MenuBar() {
        super();

        file = new JMenu("File");
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(fileExit);

        edit = new JMenu("Edit");
        JMenuItem editPreferences = new JMenuItem("Preferences");
        editPreferences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PreferencesDialog();
            }
        });
        edit.add(editPreferences);

        help = new JMenu("Help");
        add(file);
        add(edit);
        add(help);
    }

}

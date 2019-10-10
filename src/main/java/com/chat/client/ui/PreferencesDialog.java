package com.chat.client.ui;

/*
 * Copyright (C) 2013 by danjian <josepwnz@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.chat.client.ClientApplication;

import net.miginfocom.swing.MigLayout;

public class PreferencesDialog extends JDialog {
    private static final long serialVersionUID = 2711954362741187209L;
    private JTextField server;
    private JTextField port;
    private JTextField username;
    private JPasswordField password;
    private JTextField phone;
    private JTextField avatar;
    private JCheckBox saveLogs;

    public PreferencesDialog() {
        super(ClientApplication.ui.frame, "Preferences");
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnCancel);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        panel.add(btnSave);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new MigLayout("", "[150px:n,grow][250px:n,grow]", "[][][][][][][]"));

        JLabel lblServer = new JLabel("Server");
        panel_1.add(lblServer, "cell 0 0,alignx left");

        server = new JTextField(ClientApplication.config.getAddress());
        server.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(server, "cell 1 0,growx");
        server.setColumns(10);

        JLabel lblPort = new JLabel("Port");
        panel_1.add(lblPort, "cell 0 1,alignx left");

        port = new JTextField(Integer.toString(ClientApplication.config.getPort()));
        port.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(port, "cell 1 1,growx");
        port.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        panel_1.add(lblUsername, "cell 0 2,alignx left");

        username = new JTextField(ClientApplication.config.getUsername());
        username.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(username, "cell 1 2,growx");
        username.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        panel_1.add(lblPassword, "cell 0 3,alignx left");

        password = new JPasswordField(ClientApplication.config.getPassword());
        password.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(password, "cell 1 3,growx");

        JLabel lblPhone = new JLabel("Phone");
        panel_1.add(lblPhone, "cell 0 4,alignx left");

        phone = new JTextField(ClientApplication.config.getPhone());
        phone.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(phone, "cell 1 4,growx");
        phone.setColumns(10);

        JLabel lblAvatar = new JLabel("Avatar");
        panel_1.add(lblAvatar, "cell 0 5,alignx left");

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, "cell 1 5,grow");
        panel_2.setLayout(new MigLayout("", "[114px,grow][]", "[25px]"));

        avatar = new JTextField(ClientApplication.config.getAvatarPath());
        avatar.setHorizontalAlignment(SwingConstants.RIGHT);
        avatar.setEditable(false);
        panel_2.add(avatar, "cell 0 0,growx,aligny center");
        avatar.setColumns(10);

        JButton btnPath = new JButton("...");
        btnPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setCurrentDirectory(new File("."));
                int selected = chooser.showSaveDialog(ClientApplication.ui.frame);

                if (selected == JFileChooser.APPROVE_OPTION) {
                    avatar.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        panel_2.add(btnPath, "cell 1 0,alignx left,aligny top");

        JLabel lblSaveLogs = new JLabel("Save logs");
        panel_1.add(lblSaveLogs, "cell 0 6,alignx left");

        saveLogs = new JCheckBox("");
        saveLogs.setSelected(ClientApplication.config.getSaveLogs());
        panel_1.add(saveLogs, "cell 1 6,alignx right");

        pack();
        setLocationRelativeTo(ClientApplication.ui.frame);
        setVisible(true);
    }

    public void save() {
        ClientApplication.config.setUsername(username.getText());
        ClientApplication.config.setAvatarPath(avatar.getText());
        ClientApplication.config.setPhone(phone.getText());
        ClientApplication.config.setAddress(server.getText());
        ClientApplication.config.setPort(Integer.parseInt(port.getText()));
        ClientApplication.config.setPassword(new String(password.getPassword()));
        ClientApplication.config.setSaveLogs(Boolean.toString(saveLogs.isSelected()));
        ClientApplication.config.save();
        dispose();
    }
}

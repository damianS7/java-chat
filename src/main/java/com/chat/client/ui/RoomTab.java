package com.chat.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.chat.client.ClientApplication;

import net.miginfocom.swing.MigLayout;

public class RoomTab extends TabChat {
    private static final long serialVersionUID = -7400661692831081968L;
    private JListUsersRoom list;

    public RoomTab(String title) {
        super(title);
        list = new JListUsersRoom();
        outputBox.setEditable(false);
        setLayout(
                new MigLayout("", "[332px,grow][100px]", "[202px,grow][80px]"));

        JScrollPane outScroll = new JScrollPane();
        outScroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outScroll.setViewportView(outputBox);
        add(outScroll, "cell 0 0,grow");

        JScrollPane inScroll = new JScrollPane();
        inScroll.setViewportView(inputBox);
        add(inScroll, "cell 0 1,grow");

        JScrollPane listScroll = new JScrollPane();
        listScroll.setViewportView(list);
        add(listScroll, "cell 1 0,grow");

        JPanel panel = new JPanel();
        add(panel, "cell 1 1,growx,aligny top");
        panel.setLayout(new MigLayout("", "[grow]", "[][]"));

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(btnSend, "cell 0 0,growx,aligny top");

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientApplication.client.exitRoom(getTitle());
            }
        });
        panel.add(btnExit, "cell 0 1,growx,aligny top");

        inputBox.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    sendMessage();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
    }

    public void addUsers(List<String> users) {
        list.addUsers(users);
    }

    public JListUsersRoom getList() {
        return list;
    }

    @Override
    public void sendMessage() {
        String from = ClientApplication.client.getUsername();
        ClientApplication.client.sendMessageToRoom(from, "",
                inputBox.getText());
        inputBox.setText("");
    }

}

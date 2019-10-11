package com.chat.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.chat.client.ClientApplication;

import net.miginfocom.swing.MigLayout;

public class ConversationTab extends TabChat {

    private static final long serialVersionUID = 1832222818132204595L;
    private TabChat conversationTab;

    public ConversationTab(String title) {
        super(title);
        conversationTab = this;
        outputBox.setEditable(false);
        setLayout(
                new MigLayout("", "[332px,grow][100px]", "[202px,grow][80px]"));

        JScrollPane outScroll = new JScrollPane();
        outScroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outScroll.setViewportView(outputBox);
        add(outScroll, "cell 0 0 2 1,grow");

        JScrollPane inScroll = new JScrollPane();
        inScroll.setViewportView(inputBox);
        add(inScroll, "cell 0 1,grow");

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
                ClientApplication.ui.removeTab(conversationTab);
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

    @Override
    public void sendMessage() {
        String from = ClientApplication.client.getUsername();
        ClientApplication.client.sendMessageToConversation(from, "",
                inputBox.getText());
        inputBox.setText("");
    }

}

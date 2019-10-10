package com.chat.client.ui;

import javax.swing.JTextArea;

import com.chat.client.ClientApplication;

public class TabChat extends Tab {
    private static final long serialVersionUID = -4467915743235869875L;
    protected JTextArea outputBox;
    protected JTextArea inputBox;

    public TabChat(String title) {
        super(title);

        outputBox = new JTextArea();
        inputBox = new JTextArea();
    }

    public void addText(String text) {
        if (!text.endsWith("\n")) {
            text += "\n";
        }
        outputBox.append(text);
        outputBox.setCaretPosition(outputBox.getDocument().getLength());
    }

    public void sendMessage() {
        ClientApplication.client.sendMessage("userCredentials.getNickName()", getTitle(), inputBox.getText());
        inputBox.setText("");
    }

    protected void sendMessage(String where) {
        // if where == room ... if where = conversation
    }

}

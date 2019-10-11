package com.chat.client.ui;

import javax.swing.JTextArea;

public abstract class TabChat extends Tab {
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

    public abstract void sendMessage();

}

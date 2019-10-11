package com.chat.client.ui;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class LogTab extends TabChat {

    public LogTab(String title) {
        super(title);

        setLayout(new MigLayout("", "[grow]", "[grow]"));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, "cell 0 0,grow");
        scrollPane.setViewportView(outputBox);
        outputBox.setEditable(false);
    }

    public LogTab() {
        this("Server Log");
    }

    @Override
    public void sendMessage() {
    }

}

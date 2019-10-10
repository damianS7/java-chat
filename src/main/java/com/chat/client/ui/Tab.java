package com.chat.client.ui;

import javax.swing.JPanel;

public class Tab extends JPanel {
    private static final long serialVersionUID = -4467915743235869875L;
    protected String title;

    public Tab(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

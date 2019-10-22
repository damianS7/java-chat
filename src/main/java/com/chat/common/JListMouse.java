package com.chat.common;

import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

// Una lista que permite acciones con el raton

public abstract class JListMouse extends JList<String> implements MouseListener {
    protected DefaultListModel<String> model;

    public JListMouse() {
        model = new DefaultListModel<String>();
        setModel(model);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addMouseListener(this);
    }

    public void addItem(String value) {
        model.addElement(value);
    }

    public void clearList() {
        model.clear();
    }
}

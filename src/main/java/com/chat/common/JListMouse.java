package com.chat.common;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

// Una lista que permite acciones con el raton

public class JListMouse extends JList<String> implements MouseListener {
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

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

}

package com.chat.server.ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TitlePanel extends JPanel implements MouseListener, MouseMotionListener {
    private Point initialClick;
    private JFrame parent;

    public TitlePanel() {
        super();
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // get location of Window
        int thisX = parent.getLocation().x;
        int thisY = parent.getLocation().y;
        

        // Determine how much the mouse moved since the initial click
        int xMoved = e.getX() - initialClick.x;
        int yMoved = e.getY() - initialClick.y;

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        parent.setLocation(X, Y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parent = (JFrame) getRootPane().getParent();
        initialClick = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        parent = (JFrame) getRootPane().getParent();
        initialClick = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}

package com.chat.client.ui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.chat.client.ClientApplication;
import com.chat.common.JListMouse;

/**
 * Lista con los nombres de las salas disponibles
 * 
 * @author danjian
 */
public class ClientRoomJList extends JListMouse {

    private static final long serialVersionUID = 961840196718831753L;
    private List<String> rooms = new ArrayList<String>();

    public ClientRoomJList() {
	super();
    }

    public void addRooms(List<String> rooms) {
	clearRooms();
	for (String room : rooms) {
	    addRoom(room);
	}
    }

    public void addRoom(String room) {
	addItem(room);
	rooms.add(room);
    }

    public void clearRooms() {
	clearList();
	rooms.clear();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

	if (e.getClickCount() == 2) {
	    ClientApplication.client.enterRoom(getSelectedValue());
	}

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

}

package com.chat.server.ui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.chat.common.JListMouse;
import com.chat.server.database.RoomDatabaseRow;

public class ServerRoomJList extends JListMouse {
    private List<String> rooms = new ArrayList<String>();

    public ServerRoomJList(List<RoomDatabaseRow> rooms) {
	for (RoomDatabaseRow room : rooms) {
	    addRoom(room.name);
	}
    }

    public void addRoom(String acc) {
	rooms.add(acc);
	model.addElement(acc);
    }

    public void removeRoom(String acc) {
	rooms.remove(acc);
	model.remove(getSelectedIndex());
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

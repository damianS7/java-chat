package com.chat.server.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.chat.common.Room;
import com.chat.server.ServerApplication;

import net.miginfocom.swing.MigLayout;

public class RoomsPanel extends JPanel {
    private static final long serialVersionUID = -7985614931111845166L;
    private JList<String> list;
    private JTextField roomName;
    private JSpinner maxUsers;
    private JTextArea description;
    private JTextField mod;
    private DefaultListModel<String> listModel;

    public RoomsPanel() {
	super();
	setLayout(new MigLayout("", "[200px:n,right][grow][grow,fill]", "[][][][grow][][][]"));

	JScrollPane scrollPane = new JScrollPane();
	add(scrollPane, "cell 2 0 1 4,grow");

	listModel = new DefaultListModel<String>();
	list = new JList<String>();
	list.setModel(listModel);
	loadRooms();

	scrollPane.setViewportView(list);

	JLabel lblRoom = new JLabel("Room");
	add(lblRoom, "cell 0 0,alignx right");

	roomName = new JTextField();
	roomName.setHorizontalAlignment(SwingConstants.RIGHT);
	add(roomName, "cell 1 0,growx");
	roomName.setColumns(10);

	JLabel lblMaxUsers = new JLabel("Max users");
	add(lblMaxUsers, "cell 0 1,alignx right");

	maxUsers = new JSpinner();
	maxUsers.setModel(new SpinnerNumberModel(20, 1, 1000, 1));
	add(maxUsers, "cell 1 1,growx");

	JLabel lblDescription = new JLabel("Description");
	add(lblDescription, "cell 0 2");

	JScrollPane scrollPane_1 = new JScrollPane();
	add(scrollPane_1, "cell 1 2 1 2,grow");

	description = new JTextArea();
	scrollPane_1.setViewportView(description);

	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String name = list.getSelectedValue();
		if (name != null) {
		    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "delete?",
			    JOptionPane.YES_NO_OPTION);
		    if (reply == JOptionPane.YES_OPTION) {
			try {
			    ServerApplication.roomDatabase.deleteRoom(name);
			} catch (SQLException e1) {
			    e1.printStackTrace();
			}
			listModel.remove(list.getSelectedIndex());
		    }
		}
	    }
	});

	add(btnDelete, "cell 2 4,growx");

	JButton btnAddmodify = new JButton("Add/Modify");
	btnAddmodify.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String name = roomName.getText();
		String desc = description.getText();
		// int maxu = Integer.parseInt(maxUsers.getModel().getValue().toString());

		if (name.isEmpty() || desc.isEmpty()) {
		    JOptionPane.showMessageDialog(null, "Room name/description cannot be empty!");
		    return;
		}

		Room r = new Room(name, desc);
		String[] mods = mod.getText().split(",");

		for (String mod : mods) {
		    // r.addRoomModerator(mod.trim());
		}

		try {
		    ServerApplication.roomDatabase.addRoom(r);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		listModel.addElement(name);
	    }
	});

	JLabel lblMod = new JLabel("Mod");
	add(lblMod, "cell 0 4,alignx right");

	mod = new JTextField();
	mod.setHorizontalAlignment(SwingConstants.RIGHT);
	add(mod, "cell 1 4,growx");
	mod.setColumns(10);
	add(btnAddmodify, "cell 1 5,growx");

	JPanel panel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	flowLayout.setAlignment(FlowLayout.RIGHT);
	add(panel, "cell 1 6,grow");

	setVisible(true);
    }

    private void loadRooms() {
	List<Room> rooms = ServerApplication.roomDatabase.getRooms();
	for (Room row : rooms) {
	    listModel.addElement(row.name);
	}
    }

}

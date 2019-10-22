package com.chat.server.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.chat.server.ServerApplication;
import com.chat.server.database.RoomDatabaseRow;

import net.miginfocom.swing.MigLayout;

public class RoomsPanel extends JPanel {
    private static final long serialVersionUID = -7985614931111845166L;
    private ServerRoomJList list;
    private JTextField roomName;
    private JSpinner maxUsers;
    private JTextArea description;
    private JTextField mod;

    public RoomsPanel() {
	super();
	setLayout(new MigLayout("", "[grow,fill][200px:n,right][grow]", "[][][][grow][][][]"));

	JScrollPane scrollPane = new JScrollPane();
	add(scrollPane, "cell 0 0 1 4,grow");

	list = new ServerRoomJList(ServerApplication.roomDatabase.getRooms());
	scrollPane.setViewportView(list);

	JLabel lblRoom = new JLabel("Room");
	add(lblRoom, "cell 1 0,alignx right");

	roomName = new JTextField();
	roomName.setHorizontalAlignment(SwingConstants.RIGHT);
	add(roomName, "cell 2 0,growx");
	roomName.setColumns(10);

	JLabel lblMaxUsers = new JLabel("Max users");
	add(lblMaxUsers, "cell 1 1,alignx right");

	maxUsers = new JSpinner();
	maxUsers.setModel(new SpinnerNumberModel(20, 1, 1000, 1));
	add(maxUsers, "cell 2 1,growx");

	JLabel lblDescription = new JLabel("Description");
	add(lblDescription, "cell 1 2");

	JScrollPane scrollPane_1 = new JScrollPane();
	add(scrollPane_1, "cell 2 2 1 2,grow");

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
			list.removeRoom(name);
		    }
		}
	    }
	});

	add(btnDelete, "cell 0 4,growx");

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

		RoomDatabaseRow r = new RoomDatabaseRow(name, desc, "");
		String[] mods = mod.getText().split(",");

		for (String mod : mods) {
		    // r.addRoomModerator(mod.trim());
		}

		try {
		    ServerApplication.roomDatabase.addRoom(r);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		list.addRoom(name);
	    }
	});

	JLabel lblMod = new JLabel("Mod");
	add(lblMod, "cell 1 4,alignx right");

	mod = new JTextField();
	mod.setHorizontalAlignment(SwingConstants.RIGHT);
	add(mod, "cell 2 4,growx");
	mod.setColumns(10);
	add(btnAddmodify, "cell 2 5,growx");

	JPanel panel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	flowLayout.setAlignment(FlowLayout.RIGHT);
	add(panel, "cell 2 6,grow");

	setVisible(true);
    }
}

package com.chat.server.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.chat.common.BasicRoom;
import com.chat.server.ServerApplication;

import net.miginfocom.swing.MigLayout;

public class RoomsPanel extends JPanel {
    private static final long serialVersionUID = -7985614931111845166L;
    private DefaultListModel<String> listModel;
    private JList<String> roomList;
    private JTextField roomNameField;
    private JTextArea roomDescriptionField;

    public RoomsPanel() {
        super();
        setLayout(new MigLayout("", "[grow,fill][200px:n,right][grow]", "[][][][grow][]"));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 0 1 4,grow");

        listModel = new DefaultListModel<String>();
        roomList = new JList<String>();
        roomList.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                showRoomInfo();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        roomList.setModel(listModel);
        loadRooms();

        scrollPane.setViewportView(roomList);

        JLabel lblRoom = new JLabel("Room");
        add(lblRoom, "cell 1 0,alignx right");

        roomNameField = new JTextField();
        roomNameField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(roomNameField, "cell 2 0,growx");
        roomNameField.setColumns(10);

        JLabel lblDescription = new JLabel("Description");
        add(lblDescription, "cell 1 1");

        JScrollPane scrollPane_1 = new JScrollPane();
        add(scrollPane_1, "cell 2 1 1 3,grow");

        roomDescriptionField = new JTextArea();
        scrollPane_1.setViewportView(roomDescriptionField);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = roomList.getSelectedValue();

                if (roomName == null) {
                    return;
                }

                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "delete?",
                        JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        ServerApplication.roomDatabase.deleteRoom(roomName);
                        listModel.remove(roomList.getSelectedIndex());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showInternalMessageDialog(null,
                                "No se pudo" + " borrar debido a un problema con interno.");
                    }
                }
            }
        });

        JButton btnAddmodify = new JButton("Add/Modify");
        btnAddmodify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = roomNameField.getText();
                String roomDesc = roomDescriptionField.getText();

                if (roomName.isEmpty() || roomDesc.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Room name/description cannot be empty!");
                    return;
                }

                BasicRoom room = null;

                try {
                    room = ServerApplication.roomDatabase.getRoom(roomName);

                    // UPDATE
                    if (room != null) {
                        ServerApplication.roomDatabase.updateRoom(roomName, "DESCRIPTION", roomDesc);
                        return;
                    }

                    // INSERT
                    room = new BasicRoom(roomName, roomDesc);
                    ServerApplication.roomDatabase.addRoom(room);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                listModel.addElement(roomName);
            }
        });

        add(btnAddmodify, "cell 2 4,growx");
        add(btnDelete, "cell 0 4,growx");
        setVisible(true);
    }

    private void showRoomInfo() {
        String roomName = roomList.getSelectedValue();
        try {
            BasicRoom room = ServerApplication.roomDatabase.getRoom(roomName);
            roomNameField.setText(room.name);
            roomDescriptionField.setText(room.description);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void loadRooms() {
        List<BasicRoom> rooms = ServerApplication.roomDatabase.getRooms();
        for (BasicRoom room : rooms) {
            listModel.addElement(room.name);
        }
    }

}

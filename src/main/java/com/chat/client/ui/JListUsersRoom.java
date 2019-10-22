package com.chat.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.chat.client.ClientApplication;
import com.chat.common.JListMouse;

/**
 * Esta es la lista que se muestra cuando se entra en una sala y contiene todos
 * los usuarios que estan es ese momento en la sala.
 * 
 * @author Damian
 *
 */
public class JListUsersRoom extends JListMouse {
    private static final long serialVersionUID = 3861971951317131589L;
    private JPopupMenu menu;
    private List<String> users = new ArrayList<String>();

    public JListUsersRoom() {
	super();
	menu = new JPopupMenu();
	menu.addMouseListener(this);
	JMenuItem kick = new JMenuItem("Kick");
	kick.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String reason = JOptionPane.showInputDialog(null, "Reason?", "Ok", JOptionPane.YES_NO_OPTION);

		if (reason != null) {
		    String user = getModel().getElementAt(getSelectedIndex());

		    // ClientApplication.client.kick(room, user, reason);
		    // ClientApplication.client.sendPacket(new KickPacket(room, user, reason));
		}
	    }
	});

	JMenuItem ban = new JMenuItem("Ban");
	ban.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String time = JOptionPane.showInputDialog(null, "Ban time?", "Ban", JOptionPane.YES_NO_OPTION);

		String reason = JOptionPane.showInputDialog(null, "Reason?", "Ok", JOptionPane.YES_NO_OPTION);

		if (time != null && reason != null) {
		    int t = Integer.parseInt(time);
		    String user = getModel().getElementAt(getSelectedIndex());

		    // ClientApplication.client.ban(room, user, t, reason);
		}
	    }
	});
	JMenuItem mute = new JMenuItem("Mute");
	mute.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String time = JOptionPane.showInputDialog(null, "Ban time?", "Ban", JOptionPane.YES_NO_OPTION);

		String reason = JOptionPane.showInputDialog(null, "Reason?", "Ok", JOptionPane.YES_NO_OPTION);

		if (time != null && reason != null) {
		    int t = Integer.parseInt(time);
		    String user = getModel().getElementAt(getSelectedIndex());

		    // ClientApplication.client.mute(room, user, t, reason);
		}
	    }
	});
	menu.add(ban);
	menu.add(kick);
	menu.add(mute);
    }

    public void addUser(String username) {
	users.add(username);
	model.addElement(username);
    }

    public void addUsers(List<String> usersc) {
	for (String user : usersc) {
	    addUser(user);
	}
    }

    public void removeUser(String username) {
	users.remove(username);
	model.removeElement(username);
    }

    private void showPopupMenu(MouseEvent e) {
	// if (ClientApplication.client.getClientUser().getAccount().getRole() ==
	// UserRole.USER) {
	// return;
	// }

	if (e.isPopupTrigger()) {
	    // Numero de fila donde esta el puntero
	    // int row = model.rowAtPoint(e.getPoint());
	    setSelectedIndex(locationToIndex(e.getPoint()));
	    menu.show(e.getComponent(), e.getX(), e.getY());
	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
	showPopupMenu(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	showPopupMenu(e);

	if (e.getClickCount() == 2) {
	    ClientApplication.ui.addTab(new ConversationTab(model.get(getSelectedIndex())));
	}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

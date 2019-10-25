package com.chat.server.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.chat.common.BasicAccount;
import com.chat.server.ServerApplication;

import net.miginfocom.swing.MigLayout;

public class AccountsPanel extends JPanel {
    private static final long serialVersionUID = -7985614931111845166L;
    private DefaultListModel<String> listModel;
    private JList<String> accountList;
    private JTextField accountNameField;
    private JTextField accountPassowrdField;
    private JComboBox<String> roleBox;

    public AccountsPanel() {
        super();
        setLayout(new MigLayout("", "[grow,fill][200px:n,right][grow]", "[][][][grow][]"));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 0 1 4,grow");

        listModel = new DefaultListModel<String>();
        accountList = new JList<String>();
        accountList.addMouseListener(new MouseListener() {

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
        accountList.setModel(listModel);
        loadAccounts();

        scrollPane.setViewportView(accountList);

        JLabel lblUsername = new JLabel("Username");
        add(lblUsername, "cell 1 0,alignx right");

        accountNameField = new JTextField();
        accountNameField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(accountNameField, "cell 2 0,growx");
        accountNameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        add(lblPassword, "cell 1 1");

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = accountList.getSelectedValue();

                if (accountName == null) {
                    return;
                }

                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "delete?",
                        JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        ServerApplication.accountDatabase.deleteAccount(accountName);
                        listModel.remove(accountList.getSelectedIndex());
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
                String accountName = accountNameField.getText();
                String accountPassword = accountPassowrdField.getText();
                String accountRole = roleBox.getSelectedItem().toString();

                if (accountName.isEmpty() || accountPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Room name/description cannot be empty!");
                    return;
                }

                BasicAccount account = null;

                try {
                    account = ServerApplication.accountDatabase.getAccount(accountName);

                    // UPDATE
                    if (account != null) {
                        ServerApplication.accountDatabase.updateAccount(accountName, "PASSWORD", accountPassword);
                        ServerApplication.accountDatabase.updateAccount(accountName, "ROLE", accountRole);
                        return;
                    }

                    // INSERT
                    account = new BasicAccount(accountName, accountPassword, accountRole);
                    ServerApplication.accountDatabase.addAccount(account);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                listModel.addElement(accountName);
            }
        });

        accountPassowrdField = new JTextField();
        accountPassowrdField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(accountPassowrdField, "cell 2 1,grow");

        JLabel lblRole = new JLabel("Role");
        add(lblRole, "cell 1 2,alignx trailing");

        roleBox = new JComboBox<String>();
        roleBox.addItem("");
        roleBox.addItem("SUPERADMIN");
        roleBox.addItem("ADMIN");
        roleBox.addItem("MOD");
        roleBox.addItem("USER");

        add(roleBox, "cell 2 2,growx");

        add(btnAddmodify, "cell 2 4,growx");
        add(btnDelete, "cell 0 4,growx");
        setVisible(true);
    }

    private void showRoomInfo() {
        String accountName = accountList.getSelectedValue();
        try {
            BasicAccount account = ServerApplication.accountDatabase.getAccount(accountName);
            accountNameField.setText(account.getUsername());
            accountPassowrdField.setText(account.getPassword());
            roleBox.setSelectedItem(account.getRole().toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void loadAccounts() {
        List<BasicAccount> accounts = ServerApplication.accountDatabase.getAccounts();
        for (BasicAccount account : accounts) {
            listModel.addElement(account.getUsername());
        }
    }

}

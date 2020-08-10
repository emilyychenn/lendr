package gui;

import model.Contact;
import model.ContactList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *  Creates the full contact list window to view all the contacts added to a user's contact list.
 */

public class ContactsViewer {
    private static JDialog contactListDialog;
    private static JFrame mainWindow;

    // EFFECTS: constructor to initialize new window
    public ContactsViewer(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    // EFFECTS: creates a window that displays all of the users contacts
    public void createContactListWindow(ContactList contacts) {
        if (contactListDialog == null || !contactListDialog.isVisible()) {
            contactListDialog = new JDialog(mainWindow, "View Full Contact List", Dialog.ModalityType.DOCUMENT_MODAL);
            contactListDialog.setSize(500, 500);
            contactListDialog.setLocationRelativeTo(null);

            JPanel panel = initJPanel();

            List<Contact> contactList = contacts.getContacts();
            JLabel contact;
            if (contactList.size() == 0) {
                contact = new JLabel("No contacts to show.");
                panel.add(contact);
            } else {
                for (Contact c : contactList) {
                    contact = new JLabel(c.getName() + ": " + c.getContactBalance() + "\n");
                    panel.add(contact);
                }
            }

            panel.setVisible(true);
            contactListDialog.add(panel);
            contactListDialog.setVisible(true);
        } else {
            contactListDialog.requestFocus();
        }
        contactListDialog.dispatchEvent(new WindowEvent(contactListDialog, WindowEvent.WINDOW_CLOSING));
    }

    // EFFECTS: initializes jpanel to display contact names
    public JPanel initJPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(new Insets(20, 45, 50, 45)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }
}

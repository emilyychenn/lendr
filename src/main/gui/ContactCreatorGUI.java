package gui;

import model.Account;
import model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 *  Creates a new window that allows users to create a new contact, and adds that contact to the user's contact list.
 */

public class ContactCreatorGUI {
    private static JDialog contactDialog;
    private static JFrame mainWindow;
    private Account account;

    // EFFECTS: constructor to initialize new window
    public ContactCreatorGUI(JFrame mainWindow, Account myAccount) {
        this.mainWindow = mainWindow;
        this.account = myAccount;
    }

    // EFFECTS: creates window that allows user to create a new contact, then adds that contact to the contact list
    // NOTE: dialog.modalitytype.document_modal means that you can't click the main menu while the pop up menu is open!!
    public void createNewContactWindow() {
        if (contactDialog == null || !contactDialog.isVisible()) {
            contactDialog = new JDialog(mainWindow, "New Contact", Dialog.ModalityType.DOCUMENT_MODAL);
            contactDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            contactDialog.setSize(400, 200);
            contactDialog.setLocationRelativeTo(null);
            contactDialog.setLayout(null);

            JLabel promptForName = new JLabel("Enter contact's name:");
            promptForName.setBounds(50,30, 300,30);
            promptForName.setVerticalAlignment(SwingConstants.TOP);

            Container pane = contactDialog.getContentPane();
            pane.setLayout(null);
            JButton okButton = new JButton("OK");

            JTextField contactNameTextField = createjTextField();

            setContactNameText(okButton, contactNameTextField);

            pane.add(promptForName);
            pane.add(contactNameTextField);
            pane.add(okButton);
            pane.setVisible(true);
            contactDialog.setVisible(true);

        } else {
            contactDialog.requestFocus();
        }
    }

    // EFFECTS: creates a JTextField for user to input a new contact's name
    private JTextField createjTextField() {
        JTextField contactNameTextField = new JTextField();
        contactNameTextField.setLocation(45, 60);
        contactNameTextField.setSize(320, 30);
        contactNameTextField.setVisible(true);
        return contactNameTextField;
    }

    // REQUIRES: contact name cannot be the same as an exiting contact name
    // EFFECTS: creates a new contact using the name inputted in the JText Field
    private void setContactNameText(JButton okButton, JTextField contactNameTextField) {
        okButton.setBounds(50,120,50,20);
        okButton.setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputtedName = contactNameTextField.getText();

                if (account.getContactList().containsByName(inputtedName)) {
                    JOptionPane.showMessageDialog(contactDialog, inputtedName
                            + " already exists as a contact. Please enter a different name.");
                } else if ((inputtedName != null) && (inputtedName.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(contactDialog, "Contact cannot have an empty name.");
                } else {
                    Contact newContact = new Contact(inputtedName);
                    account.getContactList().addContactToList(newContact);
                    JOptionPane.showMessageDialog(contactDialog, "Contact " + inputtedName
                                                     + " added to contact list.");
                    contactDialog.dispatchEvent(new WindowEvent(contactDialog, WindowEvent.WINDOW_CLOSING));
                }
            }
        });
    }
}

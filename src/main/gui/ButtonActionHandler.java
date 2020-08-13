package gui;

import model.Account;
import model.ContactList;
import model.TransactionHistory;
import persistence.DataAccessor;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 *  Handles button presses in the main window of the LoanApp GUI.
 */

public class ButtonActionHandler extends JFrame {
    Account myAccount = new Account();
    private static final String FILE_PATH = "./data/usrAccountFile.json";
    private DataAccessor dataAccessor = new DataAccessor();
    String user;

    // EFFECTS: creates a new LoanAppGui window and handles button presses for each button
    public ButtonActionHandler() {
        LoanAppGUI.createWindow();

        handleLoadData();
        handleNewContact();
        handleAddTransaction();
        handleEditTransaction();
        handleViewContacts();
        handleSaveQuit();
    }

    // EFFECTS: saves data and quits application on button press
    private void handleSaveQuit() {
        LoanAppGUI.getSaveQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/success.wav");
                dataAccessor.saveToFile(FILE_PATH, myAccount);
                System.out.println("\nGoodbye!");
                LoanAppGUI.getFrame().dispatchEvent(new WindowEvent(LoanAppGUI.getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    // EFFECTS: displays full contact list on button press
    private void handleViewContacts() {
        LoanAppGUI.getViewContactsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/bloop.wav");
                ContactsViewerGUI contactsViewerGUI = new ContactsViewerGUI(LoanAppGUI.getFrame());
                contactsViewerGUI.createContactListWindow(myAccount.getContactList());
            }
        });
    }

    // EFFECTS: prints "edit transaction button pressed" on button press
    private void handleEditTransaction() {
        LoanAppGUI.getViewTransactionsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/bloop.wav");
                TransactionHistoryViewerGUI transactionsViewer = new TransactionHistoryViewerGUI(LoanAppGUI.getFrame());
                transactionsViewer.createTransactionHistoryWindow(myAccount.getTransactionHistory());
            }
        });
    }

    // EFFECTS: prints "add transaction button pressed" on button press
    private void handleAddTransaction() {
        LoanAppGUI.getAddTransactionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/bloop.wav");
                TransactionCreatorGUI transactionCreatorGUI =
                                new TransactionCreatorGUI(LoanAppGUI.getFrame(), myAccount);
                transactionCreatorGUI.createAddTransactionWindow();
            }
        });
    }

    // REQUIRES: given contact's name is not a duplicate of existing contact's name
    // MODIFIES: contactList
    // EFFECTS: prompts user for input to create a new contact and adds new contact to full contact list
    private void handleNewContact() {
        LoanAppGUI.getNewContactButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/bloop.wav");
                ContactCreatorGUI newContactCreatorGUI = new ContactCreatorGUI(LoanAppGUI.getFrame(), myAccount);
                newContactCreatorGUI.createNewContactWindow();
            }
        });
    }

    // MODIFIES: my account
    // EFFECTS: loads saved data from file
    private void handleLoadData() {
        LoanAppGUI.getLoadDataButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./sounds/success.wav");
                if (dataAccessor.readFromFile(FILE_PATH) != null) {
                    myAccount = dataAccessor.readFromFile(FILE_PATH);
                    user = myAccount.getName();
                    JOptionPane.showMessageDialog(LoanAppGUI.getFrame(), "Data loaded successfully.");
                } else {
                    JOptionPane.showMessageDialog(LoanAppGUI.getFrame(), "No file to load from.");
                    myAccount = new Account("New user");
                    myAccount.setContactList(new ContactList());
                    myAccount.setTransactionHistory(new TransactionHistory());
                }
            }
        });
    }

    // EFFECTS: plays a sound on button press
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}

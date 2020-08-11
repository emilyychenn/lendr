package gui;

import model.Account;
import model.Contact;
import model.DateChecker;
import model.Transaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *  Creates a new window that allows users to add a new transaction that is assigned to a contact and has an amount &
 *  a date, and adds that transaction to the user's transaction history.
 */

public class TransactionCreator {
    private static JDialog transactionDialog;
    private static JFrame mainWindow;
    private Account account;
    private DecimalFormat df = new DecimalFormat("#0.00");

    // EFFECTS: constructor to initialize new window
    public TransactionCreator(JFrame mainWindow, Account myAccount) {
        this.mainWindow = mainWindow;
        this.account = myAccount;
    }

    // EFFECTS: creates window that allows user to add a new transaction to a contact, then adds that transaction to
    //          the transaction history
    public void createAddTransactionWindow() {
        JPanel panel = initTransactionDialog();
        JLabel message;

        if (account.getContactList().countNumContacts() == 0) {
            message = new JLabel("No contacts to show. You must create a contact before adding a transaction.");
            panel.add(message);
            transactionDialog.setVisible(true);
        } else {
            JComboBox<Transaction> contactOptions = getContactNamesJComboBox(panel);
            JTextField amount = initAmountjTextField(panel);
            JTextField date = initDatejTextField(panel);

            implementOkButton(panel, contactOptions, amount, date);

            panel.setVisible(true);
            transactionDialog.setVisible(true);
        }

    }

    // EFFECTS: helper method to implement the OK button (takes in the data inputted by the user once OK is pressed)
    private void implementOkButton(JPanel panel, JComboBox<Transaction> contactOptions, JTextField amount,
                                   JTextField date) {
        JButton okButton = initOkButton(panel);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonHandler(contactOptions, amount, date);
            }
        });
    }

    // REQUIRES: valid amount entered and valid transaction date
    // MODIFIES: account, transactionHistory
    // EFFECTS: handles user input once the OK button is pressed, and checks for valid input values
    private void okButtonHandler(JComboBox<Transaction> contactOptions, JTextField amount, JTextField date) {
        Contact selectedContact = account.getContactList().getContactByName(
                                    contactOptions.getSelectedItem().toString());
        Double enteredAmount = 0.0;
        String transactionDate = "";

        enteredAmount = checkValidAmount(amount);
        if (enteredAmount == null) {
            return;
        }

        String enteredDate = date.getText();
        DateChecker dateChecker = new DateChecker();
        if (!dateChecker.isValidDate(enteredDate)) {
            transactionDate = null;
            JOptionPane.showMessageDialog(transactionDialog, "Invalid date. Enter a date between "
                                             + "Jan 1, 1900 and Dec 31, 2100");
            return;
        } else {
            transactionDate = enteredDate;
        }

        createTransaction(selectedContact, enteredAmount, transactionDate);
        showConfirmation(selectedContact, enteredAmount);
        transactionDialog.dispatchEvent(new WindowEvent(transactionDialog, WindowEvent.WINDOW_CLOSING));
    }

    // EFFECTS: initializes OK button
    private JButton initOkButton(JPanel panel) {
        JButton okButton = new JButton("OK");
        panel.add(okButton);
        okButton.setBounds(100,150,50,20);
        okButton.setVisible(true);
        return okButton;
    }

    // EFFECTS: intitializes jtextField that prompts user for transaction amount
    private JTextField initAmountjTextField(JPanel panel) {
        JTextField amount = new JTextField(10);
        JLabel promptForAmount = new JLabel("Amount (positive for amount they pay you, "
                                    + "negative for amount you pay them): $");
        panel.add(promptForAmount);
        panel.add(amount);
        return amount;
    }

    // EFFECTS: intitializes jtextField that prompts user for transaction date
    private JTextField initDatejTextField(JPanel panel) {
        JTextField date = new JTextField(10);
        JLabel promptForDate = new JLabel("Date (DD/MM/YYYY):");
        panel.add(promptForDate);
        panel.add(date);
        return date;
    }

    // EFFECTS: intitializes jComboBox that allows user to select from existing contacts to assign a contact to the new
    //          transaction
    private JComboBox<Transaction> getContactNamesJComboBox(JPanel panel) {
        List<String> contactNames = new ArrayList<String>();
        List<Contact> contacts = account.getContactList().getContacts();
        for (Contact c : contacts) {
            contactNames.add(c.getName());
        }
        String[] contactArray = contactNames.toArray(new String[0]);
        JComboBox<Transaction> contactOptions = new JComboBox(contactArray);
        panel.add(contactOptions);
        return contactOptions;
    }

    // REQUIRES: valid contact, amount, and transaction date
    // MODIFIES: transactionHistory
    // EFFECTS: creates a new transaction
    private void createTransaction(Contact selectedContact, Double enteredAmount, String transactionDate) {
        Transaction newTransaction = new Transaction(enteredAmount, selectedContact, transactionDate);
        selectedContact.addAmountToBalance(enteredAmount);
        account.getTransactionHistory().addTransaction(newTransaction);
    }

    // EFFECTS: displays confirmation message once transaction is added to history and contact's balance is changed
    private void showConfirmation(Contact selectedContact, Double amount) {
        String confirmationMsg = "";

        if (amount < 0) {
            confirmationMsg = "You are paying $" + df.format(Math.abs(amount)) + " to " + selectedContact.getName();
        } else if (amount > 0) {
            confirmationMsg = selectedContact.getName() + " is paying you $" + df.format(Math.abs(amount));
        } else {
            confirmationMsg = "No money was exchanged.";
        }

        JOptionPane.showMessageDialog(transactionDialog, confirmationMsg);
    }

    // EFFECTS: checks if user inputted amount is a valid double
    private Double checkValidAmount(JTextField amount) {
        Double enteredAmount;
        try {
            enteredAmount = Double.valueOf(amount.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(transactionDialog, "Invalid amount. Enter negative amounts in "
                                            + "form -X.XX");
            return null;
        }
        return enteredAmount;
    }

    // EFFECTS: initializes add transaction window
    private JPanel initTransactionDialog() {
        transactionDialog = new JDialog(mainWindow, "Add Transaction", Dialog.ModalityType.DOCUMENT_MODAL);
        transactionDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionDialog.setSize(700, 200);
        transactionDialog.setLocationRelativeTo(null);
        transactionDialog.setLayout(new BoxLayout(transactionDialog.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = (JPanel)transactionDialog.getContentPane();
        panel.setBorder(new EmptyBorder(20, 20, 50, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

}

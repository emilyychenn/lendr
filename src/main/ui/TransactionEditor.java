package ui;

import exceptions.InvalidDateException;
import model.Account;
import model.Contact;
import model.Transaction;
import model.TransactionHistory;

import java.util.Scanner;

/**
 *  Represents the console prompts related to editing existing transactions.
 */

public class TransactionEditor {
    Scanner input = new Scanner(System.in);

    public TransactionEditor() {
    }

    // MODIFIES: transaction + associated details
    // EFFECTS: modifies transaction details (i.e. amount, contact, date): // select a contact, view all transactions,
    //          and select a transaction to edit based on transaction ID
    public void editTransactionDetails(LoanApp app) {
        TransactionCreator transactionCreator = app.getTransactionCreator();
        ContactsViewer contactsViewer = app.getContactsViewer();
        TransactionHistory th = new TransactionHistory();
        TransactionHistoryViewer thv = app.getThv();
        System.out.print("\nSelect a contact to view their transaction history" + contactsViewer.viewContactNames(app));
        if (contactsViewer.viewContactNames(app).equals("No contacts to show.")) {
            System.out.println("\nNo contacts to show.");
        } else {
            th = thv.getTransactionHistoryFromContact(app);
            if (th == null || th.size() == 0) {
                return;
            }
        }
        String userInput = getUserInput();
        Transaction toBeEdited = null;
        toBeEdited = getTransaction(th, userInput, toBeEdited);

        if (null == toBeEdited) {
            System.out.println("Invalid transaction ID.");
        } else {
            editTransactionField(toBeEdited, app);
        }
    }

    // EFFECTS: try getting the transaction given an ID inputted by the user, and handle appropriately if doesn't exist
    public Transaction getTransaction(TransactionHistory th, String userInput, Transaction toBeEdited) {
        try {
            toBeEdited = th.getTransactionByID(userInput);
        } catch (NullPointerException e) {
            System.out.println("Invalid transaction ID.");
        }
        return toBeEdited;
    }

    // EFFECTS: returns user input for transaction ID
    public String getUserInput() {
        System.out.println("Select a transaction from the list above by entering the transaction ID.");
        return input.nextLine();
    }

    // MODIFIES: transaction
    // EFFECTS: allows user to select a field to edit
    public void editTransactionField(Transaction toBeEdited, LoanApp app) {
        TransactionCreator transactionCreator = app.getTransactionCreator();
        displayTransactionEditMenu();

        String command = input.nextLine();
        Contact chosenContact = new Contact(toBeEdited.getContact().getName());

        if (command.equals("a")) {
            changeAmount(toBeEdited);
        } else if (command.equals("c")) {
            assignNewContact(toBeEdited, app);
        } else if (command.equals("d")) {
            String dateChosen;
            try {
                dateChosen = transactionCreator.promptUserForDate(chosenContact, toBeEdited.getAmount(), app);
            } catch (InvalidDateException e) {
                return;
            }
            toBeEdited.setDateOfTransaction(dateChosen);
            System.out.println("Date changed to: " + toBeEdited.getDateOfTransaction());
        } else if (command.equals("r")) {
            return;
        } else {
            System.out.println("Invalid field.");
        }
    }

    // MODIFIES: amount in contact's balance
    // EFFECTS: changes amount owed to/from a contact
    public void changeAmount(Transaction toBeEdited) {
        toBeEdited.getContact().removeAmountFromBalance(toBeEdited.getAmount());
        editAmount(toBeEdited);
        toBeEdited.getContact().addAmountToBalance(toBeEdited.getAmount());
    }

    // EFFECTS: displays options to edit a transaction
    public void displayTransactionEditMenu() {
        System.out.println("\nWhich field would you like to edit:");
        System.out.println("\ta -> amount");
        System.out.println("\tc -> contact");
        System.out.println("\td -> date of transaction");
        System.out.println("\tr -> return to main menu");
    }

    // MODIFIES: amount of transaction
    // EFFECT: changes amount of transaction to given amount
    public void editAmount(Transaction toBeEdited) {
        System.out.println("This transaction was originally: $" + toBeEdited.getAmount());
        System.out.println("Enter new amount of transaction (positive for amount paid to you, "
                + "negative for amount you paid)");
        double userAmount;
        try {
            userAmount = Double.parseDouble(input.nextLine());
            toBeEdited.setAmount(userAmount);
            System.out.println("Amount changed to: $" + toBeEdited.getAmount());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. (Negative numbers must be in form -XXX).");
            return;
        }
    }

    // MODIFIES: contact assigned to transaction
    // EFFECT: assigns transaction to a new contact
    public void assignNewContact(Transaction toBeEdited, LoanApp app) {
        Account myAccount = app.getMyAccount();
        ContactsViewer contactsViewer = app.getContactsViewer();
        Contact chosenContact;
        System.out.println("This transaction is assigned to: " + toBeEdited.getContact().getName());
        System.out.print("\nSelect a contact to assign this transaction to:" + contactsViewer.viewContactNames(app));

        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();
        chosenContact = myAccount.getContactList().getContactByName(contactName);

        if (chosenContact == null) {
            System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                    + "main menu or any other key to select an existing contact.");
            if (!(input.nextLine().trim().equals("r"))) {
                assignNewContact(toBeEdited, app);
            } else {
                return;
            }
        } else {
            Contact originalContact = toBeEdited.getContact();
            originalContact.removeAmountFromBalance(toBeEdited.getAmount());
            toBeEdited.setContact(chosenContact);
            chosenContact.addAmountToBalance(toBeEdited.getAmount());
            System.out.println("Contact changed to: " + toBeEdited.getContact().getName());
        }
    }
}

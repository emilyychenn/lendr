package ui;

import model.Account;
import model.Contact;
import model.TransactionHistory;

import java.util.Scanner;

/**
 *  Represents the console prompts related to viewing transaction histories for a given contact.
 */

public class TransactionHistoryViewer {
    Scanner input = new Scanner(System.in);

    public TransactionHistoryViewer() {
    }

    // EFFECTS: returns a transaction history for the selected contact
    public TransactionHistory getTransactionHistoryFromContact(LoanApp app) {
        ContactsViewer contactsViewer = app.getContactsViewer();
        Contact selectedContact;
        Account myAccount = app.getMyAccount();
        TransactionHistory th;
        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();
        selectedContact = myAccount.getContactList().getContactByName(contactName);

        if (selectedContact == null) {
            contactsViewer.foundNoContact(contactName, app);
            return null;
        }
        th = myAccount.getTransactionHistory().getTransactionsByContactName(contactName);
        System.out.println("Transaction History with " + contactName + ": " + th.printTransactionHistory());
        return th;
    }

    // EFFECTS: displays transaction history
    public void viewTransactionHistory(LoanApp app) {
        ContactsViewer contactsViewer = app.getContactsViewer();
        Account myAccount = app.getMyAccount();
        Contact selectedContact;
        System.out.print("\nSelect a contact to view their transaction history: "
                + contactsViewer.viewContactNames(app));
        if (contactsViewer.viewContactNames(app).equals("No contacts to show.")) {
            System.out.println("\nNo contacts to show.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            selectedContact = myAccount.getContactList().getContactByName(contactName);

            if (selectedContact == null) {
                contactsViewer.foundNoContact(contactName, app);
                return;
            }

            System.out.println("Transaction history with " + contactName + ": ");
            TransactionHistory selectedTransactions =
                    myAccount.getTransactionHistory().getTransactionsByContactName(contactName);
            System.out.println(selectedTransactions.printTransactionHistory());
        }
    }
}
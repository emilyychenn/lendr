package ui;

import model.Account;
import model.Contact;

import java.util.Scanner;

/**
 *  Represents the console prompts related to the entire contact list.
 */

public class ContactsViewer {
    Scanner input = new Scanner(System.in);

    public ContactsViewer() {
    }

    // EFFECTS: prints full contact list and amount owed to/from each contact, positive for amount owed to user and
    //          negative for amount owed to the user's contact
    public void viewContactList(LoanApp app) {
        Account myAccount = app.getMyAccount();
        System.out.println("Contacts (positive for amount owed to you and negative for amount you owe): ");
        if (myAccount.getContactList().countNumContacts() == 0) {
            System.out.println("No contacts to show.");
        } else {
            for (int i = 0; i < myAccount.getContactList().countNumContacts(); i++) {
                Contact contact = myAccount.getContactList().getContactFromIndex(i);
                String contactName = contact.getName();
                double amountOwed = contact.getContactBalance();
                System.out.println(contactName + ": $ " + amountOwed + " owed");
            }
        }
    }

    // EFFECTS: prints only contact's names for easy selection when adding a loan or a payment
    public String viewContactNames(LoanApp app) {
        Account myAccount = app.getMyAccount();
        String contactNames = "";
        if (myAccount.getContactList().countNumContacts() == 0) {
            return "No contacts to show.";
        } else {
            for (int i = 0; i < myAccount.getContactList().countNumContacts(); i++) {
                Contact contact = myAccount.getContactList().getContactFromIndex(i);
                contactNames = contactNames.concat("\n" + contact.getName());
            }
            return contactNames;
        }
    }

    // EFFECTS: checks and returns if selected contact is valid; otherwise returns null
    public Contact retrieveSelectedContact(LoanApp app) {
        Account myAccount = app.getMyAccount();
        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();
        Contact selectedContact = myAccount.getContactList().getContactByName(contactName);

        if (selectedContact == null) {
            foundNoContactForTransaction(contactName, app);
            return null;
        }
        return selectedContact;
    }

    // EFFECTS: prompts user when given contact name does not belong to an existing contact (when adding a loan)
    public void foundNoContact(String contactName, LoanApp app) {
        TransactionHistoryViewer thv = app.getThv();
        TransactionCreator transactionCreator = app.getTransactionCreator();
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (!(input.nextLine().trim().equals("r"))) {
            thv.viewTransactionHistory(app);
        }
    }

    // EFFECTS: prompts user when given contact name does not belong to an existing contact (when adding a loan)
    public void foundNoContactForTransaction(String contactName, LoanApp app) {
        TransactionCreator transactionCreator = app.getTransactionCreator();
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (!(input.nextLine().trim().equals("r"))) {
            transactionCreator.addNewTransaction(app);
        }
    }

}

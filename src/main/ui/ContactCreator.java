package ui;

import model.Account;
import model.Contact;

import java.util.Scanner;

/**
 *  Represents the console prompts related to creating contacts.
 */

public class ContactCreator {
    Scanner input = new Scanner(System.in);

    public ContactCreator() {
    }

    // MODIFIES: this
    // EFFECTS: creates a new contact to whom a loan or a payment can be added
    public void createContact(LoanApp app) {
        System.out.println("Contact's name: ");
        String contactName = input.nextLine();
        Account myAccount = app.getMyAccount();

        if (myAccount.getContactList().containsByName(contactName)) {
            System.out.println(contactName + " already exists as a contact. Please enter a different name.");
        } else {
            Contact newContact = new Contact(contactName);
            myAccount.getContactList().addContactToList(newContact);
            System.out.println("Contact " + contactName + " added to contact list.");
        }
    }

    // EFFECTS: prints balance owed to a specific contact
    public void printBalance(Contact contact, Double amountOwed) {
        if (amountOwed < 0) {
            System.out.println("You paid $" + Math.abs(amountOwed) + " to " + contact.getName());
        } else if (amountOwed > 0) {
            System.out.println(contact.getName() + " paid you $" + Math.abs(amountOwed));
        } else {
            System.out.println("Congratulations! You owe each other nothing.");
        }
    }
}

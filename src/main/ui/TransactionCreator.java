package ui;

import exceptions.InvalidDateException;
import model.*;

import java.util.Scanner;

/**
 *  Represents the console prompts related to adding new transactions.
 */

public class TransactionCreator {
    Scanner input = new Scanner(System.in);

    public TransactionCreator() {
    }

    // REQUIRES: valid amount (double)
    // MODIFIES: this
    // EFFECTS: conducts a new transaction
    public void addNewTransaction(LoanApp app) {
        ContactsViewer contactsViewer = app.getContactsViewer();
        System.out.print("\nContact List: " + contactsViewer.viewContactNames(app));
        if (contactsViewer.viewContactNames(app).equals("No contacts to show.")) {
            System.out.println("\nYou must create a contact before adding a transaction.");
        } else {
            Contact selectedContact = contactsViewer.retrieveSelectedContact(app);
            if (selectedContact == null) {
                return;
            }
            askForTransactionAmount();
            double amount;
            try {
                amount = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. (Negative numbers must be in form -XXX).");
                return;
            }

            try {
                promptUserForDate(selectedContact, amount, app);
            } catch (InvalidDateException e) {
                addNewTransaction(app);
            }
        }
    }

    // EFFECTS: prompts user for a date and checks that the given date is valid
    public String promptUserForDate(Contact selectedContact, double amount, LoanApp app) throws InvalidDateException {
        Account myAccount = app.getMyAccount();
        ContactCreator contactCreator = app.getContactCreator();
        System.out.println("Enter date of transaction (DD/MM/YYYY): ");
        String date = input.nextLine();
        if (DateChecker.isValidDate(date)) {
            selectedContact.addAmountToBalance(amount);
            myAccount.getTransactionHistory().addTransaction(new Transaction(amount, selectedContact, date));
            contactCreator.printBalance(selectedContact, amount);
            return date;
        } else {
            throw new InvalidDateException(date, "Invalid date.");
        }
    }

    // EFFECTS: prompts user for transaction amount
    public void askForTransactionAmount() {
        System.out.print("Enter transaction amount (positive for amount they pay you, negative for amount you pay "
                + "them): $");
    }

}

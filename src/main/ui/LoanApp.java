package ui;

import exceptions.InvalidDateException;
import model.*;
import persistence.DataAccessor;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 *  Represents the main source of interaction between the user and the console. The methods runLoanApp(), init(),
 *  displayMenu(), and processCommand() are based off the TellerApp example presented in class.
 */

public class LoanApp {
    private Scanner input;
    private DataAccessor dataAccessor = new DataAccessor();
    Account newAccount;
    ContactList contactList;
    TransactionHistory transactionHistory;
    String user;
    String contactName;
    private static final String ERROR_MSG = " is an invalid date. Please re-select contact and re-enter date in format"
                                            + " DD/MM/YYYY with a date on or before Dec 31, 2100.";

    // EFFECT: runs the money loaning application
    public LoanApp() {
        runLoanApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLoanApp() {
        System.out.println("Welcome to the Money Loaning Tracker 💵");
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        System.out.println("Enter your name: ");
        user = input.nextLine();
        init(user);
        System.out.println("Hello, " + user + ".");

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        saveData();
    }


    // EFFECTS: asks user if they wish to save their info, automatically saves if they still don't have a valid
    //          response after 3 rounds of prompting
    private void saveData() {
        boolean continueAsking = true;
        int countTimesAsked = 0;
        System.out.println("Would you like to save your data? Enter 'Y' or 'N' for yes/no: ");
        while (continueAsking) {
            countTimesAsked++;
            if (input.nextLine().equalsIgnoreCase("Y") | countTimesAsked == 3) {
                dataAccessor.saveToFile(newAccount);
                continueAsking = false;
            } else if (input.nextLine().equalsIgnoreCase("N")) {
                continueAsking = false;
            } else {
                System.out.println("Invalid response. Enter 'Y' or 'N' for yes/no.");
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: initializes user's account and contact list
    private void init(String name) {
        newAccount = new Account(name);
        contactList = new ContactList();
        newAccount.setContactList(contactList);
        transactionHistory = new TransactionHistory();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create a new contact");
        System.out.println("\ta -> add a transaction"); // TODO: add a description field for transaction? or some way to tag as a loan and repayment?
        System.out.println("\te -> edit the details of a transaction");
        System.out.println("\tv -> view contact list");
        System.out.println("\tt -> view transaction history");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createContact();
        } else if (command.equals("a")) {
            addNewTransaction();
        } else if (command.equals("e")) {
            editTransactionDetails();
        } else if (command.equals("v")) {
            viewContactList();
        } else if (command.equals("t")) {
            viewTransactionHistory();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new contact to whom a loan or a payment can be added
    public void createContact() {
        System.out.println("Contact's name: ");
        contactName = input.nextLine();

        if (contactList.containsByName(contactName)) {
            System.out.println(contactName + " already exists as a contact. Please enter a different name.");
        } else {
            Contact newContact = new Contact(contactName);
            contactList.addContactToList(newContact);
            System.out.println("Contact " + contactName + " added to contact list.");
        }
    }


    // REQUIRES: valid amount (double)
    // MODIFIES: this
    // EFFECTS: conducts a new loan transaction
    private void addNewTransaction() {
        System.out.print("\nContact List: " + viewContactNames());
        if (viewContactNames().equals("No contacts to show.")) {
            System.out.println("\nYou must create a contact before adding a transaction.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            Contact selectedContact = contactList.getContactByName(contactName);

            if (selectedContact == null) {
                foundNoContactForTransaction(contactName);
                return;
            }

            System.out.print("Enter transaction amount (positive for amount they pay you, negative for amount you pay "
                    + "them): $");
            double amount;
            try {
                amount = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. (Negative numbers must be in form $-XXX).");
                return;
            }

            String date = promptUserForDate(selectedContact, amount);
            // TODO: catch invalid date exception here
            Transaction newTransaction = new Transaction(amount, selectedContact, date);
            transactionHistory.addTransaction(newTransaction);
        }
    }

    // EFFECTS: prompts user when given contact name does not belong to an existing contact (when adding a loan)
    private void foundNoContactForTransaction(String contactName) {
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (!(input.nextLine().trim().equals("r"))) {
            addNewTransaction();
        }
    }

    // EFFECTS: prompts user for a date and checks that the given date is valid
    private String promptUserForDate(Contact selectedContact, double amount) {
        System.out.println("Enter date of transaction (DD/MM/YYYY): ");
        String date = input.nextLine();
        if (isValidDate(date)) {
            selectedContact.addTransaction(amount, date);
            printBalance(selectedContact);
            return date;
        } else {
            addNewTransaction();
            return null; // TODO: change this to throw an invalid date exception!!! and then catch the exception above
        }
    }

    // REQUIRES: valid date format 'DD/MM/YYYY' and valid date
    // EFFECTS: checks that the date is in the correct format and is indeed a real date
    // TODO: move to invaliddateException!!
    public static boolean isValidDate(String dateToValidate) {
        String dateFormat = "dd/MM/yyyy";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); TODO: delete if nothing breaks

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
            Date latestDate = new Date(2100, 12, 31);
            if (date.after(latestDate)) {
                throw new InvalidDateException(dateToValidate, ERROR_MSG);
            }
            System.out.println(dateToValidate + " is a valid date.");
        } catch (DateTimeParseException | ParseException | InvalidDateException e) {
            System.out.println(dateToValidate + ERROR_MSG);
            return false;
        }
        return true;
    }

    public void editTransactionDetails() {
        // select a contact, view all transactions, and select a transaction to edit based on transaction ID
        Contact selectedContact;
        TransactionHistory th = new TransactionHistory();
        System.out.print("\nSelect a contact to view their transaction history" + viewContactNames());
        if (viewContactNames().equals("No contacts to show.")) {
            System.out.println("\nNo contacts to show.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            selectedContact = contactList.getContactByName(contactName);

            if (selectedContact == null) {
                foundNoContact(contactName);
                return;
            }
            th = transactionHistory.getTransactionsByContactName(contactName);
            System.out.println("Transaction History with " + contactName + ": " + th.printTransactionHistory(th));
        }

        if (th.equals("No transactions to show.")) {
            return;
        }

        System.out.println("Select a transaction from the list above by entering the transaction ID.");
        String userInput = input.nextLine();
        Transaction toBeEdited = th.getTransactionByID(userInput);
        if (toBeEdited.equals(null)) {
            System.out.println("Invalid transaction ID.");
        } else {
            System.out.println("\nWhich field would you like to edit:");
            System.out.println("\ta -> amount");
            System.out.println("\tc -> contact");
            System.out.println("\td -> date of transaction");
            System.out.println("\tr -> return to main menu");

            String command = input.nextLine();
            Contact chosenContact = new Contact("Unnamed");

            if (command.equals("a")) {
                System.out.println("This transaction was originally: $" + toBeEdited.getAmount());
                System.out.println("Enter new amount of transaction (positive for amount paid to you, "
                        + "negative for amount you paid");
                Double userAmount = Double.parseDouble(input.nextLine()); // todo: Catch exception if not double!!
                toBeEdited.setAmount(userAmount);
                System.out.println("Amount changed to: $" + toBeEdited.getAmount());
                return;
            } else if (command.equals("c")) {
                System.out.println("This transaction is assigned to: " + toBeEdited.getContact().getName());
                System.out.print("\nSelect a contact to assign this transaction to:" + viewContactNames());

                System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
                String contactName = input.nextLine();

                chosenContact = contactList.getContactByName(contactName);

                if (chosenContact.equals(null)) {
                    foundNoContact(contactName);
                } else {
                    toBeEdited.setContact(chosenContact);
                }
                System.out.println("Contact changed to: " + toBeEdited.getContact().getName());
                return;
            } else if (command.equals("d")) {
                String dateChosen = promptUserForDate(chosenContact, toBeEdited.getAmount());
                if (dateChosen.equals(null)) {
                    return;
                } else {
                    toBeEdited.setDateOfTransaction(dateChosen);
                    System.out.println("Date changed to: " + toBeEdited.getDateOfTransaction());
                }
            } else if (command.equals("r")) {
                return;
            } else {
                System.out.println("Invalid field."); // TODO: re-display menu above!
            }
        }
    }

    // EFFECTS: prints balance owed to a specific contact
    public void printBalance(Contact contact) {
        double amountOwed = contact.getTotalAmountOwed();

        if (amountOwed < 0) {
            System.out.println("You owe $" + amountOwed + " to " + contact.getName());
        } else if (amountOwed > 0) {
            System.out.println(contact.getName() + " owes you $" + amountOwed);
        } else {
            System.out.println("Congratulations! You owe each other nothing.");
        }
    }

    // EFFECTS: prints full contact list and amount owed to/from each contact, positive for amount owed to user and
    //          negative for amount owed to the user's contact
    public void viewContactList() {
        if (contactList.getNumContacts() == 0) {
            System.out.println("No contacts to show.");
        } else {
            for (int i = 0; i < contactList.getNumContacts(); i++) {
                Contact contact = contactList.getContactFromIndex(i);
                String contactName = contact.getName();
                double amountOwed = contact.getTotalAmountOwed();
                System.out.println(contactName + ": $ " + amountOwed + " owed");
            }
        }
    }

    // EFFECTS: prints only contact's names for easy selection when adding a loan or a payment
    public String viewContactNames() {
        String contactNames = "";
        if (contactList.getNumContacts() == 0) {
            return "No contacts to show.";
        } else {
            for (int i = 0; i < contactList.getNumContacts(); i++) {
                Contact contact = contactList.getContactFromIndex(i);
                contactNames = contactNames.concat("\n" + contact.getName());
            }
            return contactNames;
        }
    }

    public void viewTransactionHistory() {
        Contact selectedContact;
        System.out.print("\nSelect a contact to view their transaction history:" + viewContactNames());
        if (viewContactNames().equals("No contacts to show.")) {
            System.out.println("\nNo contacts to show.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            if (contactName.equals("f")) { // TODO: add a check to make sure no contacts are named 'f'
                System.out.println("Full Transaction History: ");
                System.out.println(transactionHistory.getTransactions().toString());
                return;
            } else {
                selectedContact = contactList.getContactByName(contactName);
            }

            if (selectedContact == null) {
                foundNoContact(contactName);
                return;
            }

            System.out.println("Transaction history with " + contactName + ": ");
            TransactionHistory selectedTransactions = transactionHistory.getTransactionsByContactName(contactName);
            System.out.println(selectedTransactions.printTransactionHistory(selectedTransactions));
        }
    }

    // EFFECTS: prompts user when given contact name does not belong to an existing contact (when adding a loan)
    private void foundNoContact(String contactName) {
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (!(input.nextLine().trim().equals("r"))) {
            viewTransactionHistory();
        }
    }


    // EFFECTS: initializes a new transaction and adds it to the contact's transaction history
    public void initNewTransaction(Contact selectedContact, double amount, String date) {
        Transaction newTransaction = new Transaction(amount, selectedContact, date);
        selectedContact.addTransactionToAmountOwed(amount);
        selectedContact.addTransactionToHistory(newTransaction);
    }

}
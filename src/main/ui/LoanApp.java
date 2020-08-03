package ui;

import exceptions.InvalidDateException;
import model.Account;
import model.Contact;
import model.ContactList;
import model.Transaction;
import model.TransactionHistory;
import persistence.DataAccessor;

import java.time.format.DateTimeParseException;
import java.util.Calendar;
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
    Account myAccount;
    String user;
    String contactName;
    private static final String ERROR_MSG = " is an invalid date. Date must be in format DD/MM/YYYY with a date "
                                             + "between Jan 1, 1900 and Dec 31, 2100.";
    private static final String FILE_PATH = "./data/usrAccountFile.json";

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
        reloadSavedData();
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

    // MODIFIES: myAccount
    // EFFECTS: reloads saved data if user wants to and saved data exists, otherwise creates a new account
    private void reloadSavedData() {
        if (dataAccessor.readFromFile(FILE_PATH) != null) {
            System.out.println("Would you like to reload your data? Enter 'Y' or 'N' for yes/no: ");
            String userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("Y")) {
                myAccount = dataAccessor.readFromFile(FILE_PATH);
                user = myAccount.getName();
            } else if (userInput.equalsIgnoreCase("N")) {
                System.out.println("Enter your name: ");
                user = input.nextLine();
                init(user);
            } else {
                System.out.println("Invalid selection. Will load program without your data. If you wish to load"
                        + " your data, quit the program without saving and select 'Y' next time.");
            }
        } else {
            System.out.println("No file to load from.");
            System.out.println("Enter your name: ");
            user = input.nextLine();
            init(user);
        }
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
                dataAccessor.saveToFile(FILE_PATH, myAccount);
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
        myAccount = new Account(name);
        myAccount.setContactList(new ContactList());
        myAccount.setTransactionHistory(new TransactionHistory());
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create a new contact");
        System.out.println("\ta -> add a transaction");
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

        if (myAccount.getContactList().containsByName(contactName)) {
            System.out.println(contactName + " already exists as a contact. Please enter a different name.");
        } else {
            Contact newContact = new Contact(contactName);
            myAccount.getContactList().addContactToList(newContact);
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
            Contact selectedContact = retrieveSelectedContact();
            if (selectedContact == null) {
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

            String date = "";
            try {
                date = promptUserForDate(selectedContact, amount);
            } catch (InvalidDateException e) {
                addNewTransaction();
                return;
            }
        }
    }

    // EFFECTS: checks and returns if selected contact is valid; otherwise returns null
    private Contact retrieveSelectedContact() {
        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();
        Contact selectedContact = myAccount.getContactList().getContactByName(contactName);

        if (selectedContact == null) {
            foundNoContactForTransaction(contactName);
            return null;
        }
        return selectedContact;
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
    private String promptUserForDate(Contact selectedContact, double amount) throws InvalidDateException {
        System.out.println("Enter date of transaction (DD/MM/YYYY): ");
        String date = input.nextLine();
        if (isValidDate(date)) {
            selectedContact.addAmountToBalance(amount);
            myAccount.getTransactionHistory().addTransaction(new Transaction(amount, selectedContact, date));
            printBalance(selectedContact);
            return date;
        } else {
            throw new InvalidDateException(date, "Invalid date.");
        }
    }

    // REQUIRES: valid date format 'DD/MM/YYYY' and valid date
    // EFFECTS: checks that the date is in the correct format and is indeed a real date
    // TODO: move to invaliddateException!!
    public static boolean isValidDate(String dateToValidate) {
        String dateFormat = "dd/MM/yyyy";

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
//            Date latestDate = Calendar.set(200, 11, 31);
            Date latestDate = new Date(2100, Calendar.DECEMBER, 31);
//            Date earliestDate = new Date(2000, Calendar.JANUARY, 1);
            if (date.after(latestDate)) {
//            if (date.after(latestDate) | date.before(earliestDate)) {
                throw new InvalidDateException(dateToValidate, ERROR_MSG);
            }
            System.out.println(dateToValidate + " is a valid date.");
        } catch (DateTimeParseException | ParseException | InvalidDateException e) {
            System.out.println(dateToValidate + ERROR_MSG);
            return false;
        }
        return true;
    }

    // MODIFIES: transaction + associated details
    // EFFECTS: modifies transaction details (i.e. amount, contact, date)
    public void editTransactionDetails() {
        // select a contact, view all transactions, and select a transaction to edit based on transaction ID
        TransactionHistory th = new TransactionHistory();
        System.out.print("\nSelect a contact to view their transaction history" + viewContactNames());
        if (viewContactNames().equals("No contacts to show.")) {
            System.out.println("\nNo contacts to show.");
        } else {
            th = getTransactionHistoryFromContact();
            if (th == null) {
                return;
            }
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
            editTransactionField(toBeEdited);
        }
    }

    // MODIFIES: transaction
    // EFFECTS: allows user to select a field to edit
    private void editTransactionField(Transaction toBeEdited) {
        displayTransactionEditMenu();

        String command = input.nextLine();
        Contact chosenContact = new Contact(toBeEdited.getContact().getName());

        if (command.equals("a")) {
            toBeEdited.getContact().removeAmountFromBalance(toBeEdited.getAmount());
            editAmount(toBeEdited);
            toBeEdited.getContact().addAmountToBalance(toBeEdited.getAmount());
        } else if (command.equals("c")) {
            assignNewContact(toBeEdited);
        } else if (command.equals("d")) {
            String dateChosen = "";
            try {
                dateChosen = promptUserForDate(chosenContact, toBeEdited.getAmount());
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

    // EFFECTS: displays options to edit a transaction
    private void displayTransactionEditMenu() {
        System.out.println("\nWhich field would you like to edit:");
        System.out.println("\ta -> amount");
        System.out.println("\tc -> contact");
        System.out.println("\td -> date of transaction");
        System.out.println("\tr -> return to main menu");
    }

    // MODIFIES: amount of transaction
    // EFFECT: changes amount of transaction to given amount
    private void editAmount(Transaction toBeEdited) {
        System.out.println("This transaction was originally: $" + toBeEdited.getAmount());
        System.out.println("Enter new amount of transaction (positive for amount paid to you, "
                + "negative for amount you paid)");
        Double userAmount = Double.parseDouble(input.nextLine()); // todo: Catch exception if not double!!
        toBeEdited.setAmount(userAmount);
        System.out.println("Amount changed to: $" + toBeEdited.getAmount());
    }

    // MODIFIES: contact assigned to transaction
    // EFFECT: assigns transaction to a new contact
    private void assignNewContact(Transaction toBeEdited) {
        Contact chosenContact;
        System.out.println("This transaction is assigned to: " + toBeEdited.getContact().getName());
        System.out.print("\nSelect a contact to assign this transaction to:" + viewContactNames());

        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();

        chosenContact = myAccount.getContactList().getContactByName(contactName);

        if (chosenContact.equals(null)) {
            foundNoContact(contactName);
        } else {
            Contact originalContact = toBeEdited.getContact();
            originalContact.removeAmountFromBalance(toBeEdited.getAmount());
            toBeEdited.setContact(chosenContact);
            chosenContact.addAmountToBalance(toBeEdited.getAmount());
        }
        System.out.println("Contact changed to: " + toBeEdited.getContact().getName());
    }

    // EFFECTS: returns a transaction history for the selected contact
    private TransactionHistory getTransactionHistoryFromContact() {
        Contact selectedContact;
        TransactionHistory th;
        System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
        String contactName = input.nextLine();
        selectedContact = myAccount.getContactList().getContactByName(contactName);

        if (selectedContact == null) {
            foundNoContact(contactName);
            return null;
        }
        th = myAccount.getTransactionHistory().getTransactionsByContactName(contactName);
        System.out.println("Transaction History with " + contactName + ": " + th.printTransactionHistory());
        return th;
    }

    // EFFECTS: prints balance owed to a specific contact
    public void printBalance(Contact contact) {
        double amountOwed = contact.getContactBalance();

        if (amountOwed < 0) {
            System.out.println(contact.getName() + " paid you $" + Math.abs(amountOwed));
        } else if (amountOwed > 0) {
            System.out.println("You paid $" + Math.abs(amountOwed) + " to " + contact.getName());
        } else {
            System.out.println("Congratulations! You owe each other nothing.");
        }
    }

    // EFFECTS: prints full contact list and amount owed to/from each contact, positive for amount owed to user and
    //          negative for amount owed to the user's contact
    public void viewContactList() {
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
    public String viewContactNames() {
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

    // EFFECTS: displays transaction history
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
                System.out.println(myAccount.getTransactionHistory().printTransactionHistory());
//                System.out.println(transactionHistory.getTransactions().toString());
                return;
            } else {
                selectedContact = myAccount.getContactList().getContactByName(contactName);
            }

            if (selectedContact == null) {
                foundNoContact(contactName);
                return;
            }

            System.out.println("Transaction history with " + contactName + ": ");
            TransactionHistory selectedTransactions =
                    myAccount.getTransactionHistory().getTransactionsByContactName(contactName);
            System.out.println(selectedTransactions.printTransactionHistory());
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


}
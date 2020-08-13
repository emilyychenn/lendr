package ui;

import model.*;
import persistence.DataAccessor;


import java.util.Scanner;

/**
 *  Represents the main source of interaction between the user and the console, that handles each of the different
 *  options presented to the user in the main menu.
 */

public class LoanApp {
    private Scanner input;
    private DataAccessor dataAccessor = new DataAccessor();
    private Account myAccount;
    private String user;
    private static final String FILE_PATH = "./data/usrAccountFile.json";
    private ContactCreator contactCreator = new ContactCreator();
    private TransactionCreator transactionCreator = new TransactionCreator();
    private TransactionEditor transactionEditor = new TransactionEditor();
    private ContactsViewer contactsViewer = new ContactsViewer();
    private TransactionHistoryViewer thv = new TransactionHistoryViewer();

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
            String userInput = input.nextLine().trim();
            if (userInput.equalsIgnoreCase("Y") | countTimesAsked == 3) {
                dataAccessor.saveToFile(FILE_PATH, myAccount);
                continueAsking = false;
            } else if (userInput.equalsIgnoreCase("N")) {
                continueAsking = false;
            } else {
                System.out.println("Invalid response. Enter 'Y' or 'N' for yes/no.");
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: initializes user's account and contact list
    public void init(String name) {
        myAccount = new Account(name);
        myAccount.setContactList(new ContactList());
        myAccount.setTransactionHistory(new TransactionHistory());
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
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
    public void processCommand(String command) {
        if (command.equals("c")) {
            contactCreator.createContact(this);
        } else if (command.equals("a")) {
            transactionCreator.addNewTransaction(this);
        } else if (command.equals("e")) {
            transactionEditor.editTransactionDetails(this);
        } else if (command.equals("v")) {
            contactsViewer.viewContactList(this);
        } else if (command.equals("t")) {
            thv.viewTransactionHistory(this);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public ContactCreator getContactCreator() {
        return contactCreator;
    }

    public TransactionCreator getTransactionCreator() {
        return transactionCreator;
    }

    public TransactionHistoryViewer getThv() {
        return thv;
    }

    public ContactsViewer getContactsViewer() {
        return contactsViewer;
    }
}
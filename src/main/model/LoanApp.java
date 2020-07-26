package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LoanApp {
    private Scanner input;
    Account newAccount;
    ContactList contactList;
    String user;

    public LoanApp() {
        runLoanApp();
    }

    // TODO: methods to be implemented:
    // basic constructor
    // calculateTotalBalance()
    // addContact()
    // selectContact()
    // listContacts() // TODO: should this be included?? it's already in account...

    // TODO: based off TellerApp (should this be referenced/cited somewhere??)
    private void runLoanApp() {
        System.out.println("Welcome to the Money Loaning Tracker 💵");
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        System.out.println("Enter your name: ");
        user = input.next();
        loadAccounts(user); // TODO!!

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts(String name) {
//        try {
//            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
//            cheq = accounts.get(0);
//            sav = accounts.get(1);
//        } catch (IOException e) {
//            init(name);
//        }
    }

    // MODIFIES: this
    // EFFECTS: initializes account and contact list
    private void init(String name) {
        newAccount = new Account(name);
        contactList = new ContactList();
        Contact newContact = new Contact("sample contact", 1234567890);
        contactList.addContactToList(newContact);
    }

    // TODO: EDIT THIS MENU TO REFLECT APPROPRIATE OPTIONS
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add loan");
        System.out.println("\tc -> create a new contact");
        System.out.println("\tp -> enter a payment");
//        System.out.println("\te -> edit loan details");
//        System.out.println("\ts -> save transaction history to file");
        System.out.println("\tv -> view contact list");
        System.out.println("\tl -> view list of loans");
        System.out.println("\tq -> quit");
    }

    // TODO: EDIT THIS TO REFLECT APPROPRIATE OPTIONS
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addLoan();
        } else if (command.equals("c")) {
            System.out.println("Contact's name: ");
            String contactName = input.next();
            System.out.println("Contact's phone number: ");
            long phoneNum = input.nextLong(); //test for special characters, too many digits, etc

            Contact newContact = new Contact(contactName, phoneNum);
            System.out.println(newContact.getName());
            System.out.println(newContact.getPhoneNum());
            contactList.addContactToList(newContact); // TODO: FIX
        } else if (command.equals("p")) {
//            addPayment(); // TODO!!
        } else if (command.equals("e")) {
//            editLoan();   // TODO!!
        } else if (command.equals("s")) {
//            saveTransactionHistory();    // TODO!!
        } else if (command.equals("v")) {
            viewContactList();
        } else if (command.equals("l")) {
//            viewListOfLoans();    // TODO!!
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void addLoan() {
        System.out.print("Enter loan amount: $");
        double amount = input.nextDouble();

        System.out.println("Enter contact name: ");
        String contactName = input.next();

//        Contact selectContact = getContactByName(contactName); // TODO: method exists in contactlist, should it be moved?

        while (amount == 0) {
            if (amount < 0) {
//                System.out.println("You owe " + amount + " to" + selectContact.getName());
            } else if (amount > 0) {
//                System.out.println(selectContact.getName() + " owes you " + amount);
            } else {
                System.out.println("Please enter a non-zero amount. You cannot create a $0 loan:");
                amount = input.nextDouble();
            }
        }

//        selectContact.deposit(amount);

//        printBalance(selectContact);
    }


    public void viewContactList() {
        for (int i = 0; i < contactList.getNumContacts(); i++) {
            Contact contact = contactList.getContactFromIndex(i);
            String contactName = contact.getName();
            long phoneNum = contact.getPhoneNum();
            double amountOwed = contact.getTotalAmountOwed();
            System.out.println(contactName + ": " + amountOwed + " (" + phoneNum + ")");
        }
    }

}

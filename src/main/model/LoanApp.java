package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
        init(user);
        loadAccounts(user); // TODO!!
        System.out.println("Hello, " + user + ".");

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
            System.out.println("Contact's phone number, entered as XXX-XXX-XXXX: ");
            String phoneNum = input.next(); //test for special characters, too many digits, etc

            Contact newContact = new Contact(contactName, phoneNum);
            contactList.addContactToList(newContact);
            System.out.println("Contact " + contactName + " added to contact list.");
        } else if (command.equals("p")) {
//            addPayment(); // TODO!!
        } else if (command.equals("e")) {
//            editLoan();   // TODO!!
        } else if (command.equals("s")) {
//            saveTransactionHistory();    // TODO!!
        } else if (command.equals("v")) {
            viewContactList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void addLoan() {
        System.out.print("Contact List: ");
        System.out.println(viewContactNames());
        if (viewContactNames() == "No contacts to show.") {
            System.out.println("You must create a contact before adding a loan.");
        } else {
            System.out.println("Enter a contact from list above (name is not case sensitive): ");
            String contactName = input.next();

            System.out.print("Enter loan amount (positive for amount they owe you, negative for amount you owe them): $");
            double amount = input.nextDouble();

            System.out.println("Enter date of loan (MM/DD/YY), or 't' for today's date: ");
            String date = input.next();
            if (date == "t") {
                date = LocalDate.now().toString();
            } else if (! date.matches("\\(0?[1-9]|1[012])-\\(0?[1-9]|[12][0-9]|3[01])-\\d{2}")) {
                System.out.println("Invalid date. Please enter date in format MM/DD/YY.");
            }

            Contact selectedContact = contactList.getContactByName(contactName);
            if (selectedContact == null) {
                System.out.println(contactName + " doesn't exist in your contact list. Select a contact from "
                        + "your existing contacts or create a new contact.");
            }
            Loan newLoan = new Loan(amount, date);
            selectedContact.setTotalAmountOwed(amount);

            printBalance(selectedContact);
        }
    }

    public void printBalance(Contact contact) {
        double amountOwed = contact.getTotalAmountOwed();

        if (amountOwed < 0) {
            System.out.println("You owe " + amountOwed + " to " + contact.getName());
        } else if (amountOwed > 0) {
            System.out.println(contact.getName() + " owes you " + amountOwed);
        } else {
            System.out.println("Congratulations! You owe each other nothing.");
        }
    }


    public void viewContactList() {
        if (contactList.getNumContacts() == 0) {
            System.out.println("No contacts to show.");
        } else {
            for (int i = 0; i < contactList.getNumContacts(); i++) {
                Contact contact = contactList.getContactFromIndex(i);
                String contactName = contact.getName();
                String phoneNum = contact.getPhoneNum();
                double amountOwed = contact.getTotalAmountOwed();
                System.out.println(contactName + ": $ " + amountOwed + " owed, phone #: (" + phoneNum + ")");
            }
        }
    }

    public String viewContactNames() {
        String contactNames = "";
        if (contactList.getNumContacts() == 0) {
            return "No contacts to show.";
        } else {
            for (int i = 0; i < contactList.getNumContacts(); i++) {
                Contact contact = contactList.getContactFromIndex(i);
                String contactName = contact.getName();
                contactNames = contactNames + "\n" + contactName;
            }
            return contactNames;
        }
    }

    public void addPayment() {

    }

}

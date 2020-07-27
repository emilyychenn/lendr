package ui;

import model.*;

import java.time.LocalDate;
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
    Account newAccount;
    ContactList contactList;
    String user;
    String contactName;

    // EFFECT: runs the money loaning application
    public LoanApp() {
        runLoanApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLoanApp() {
        System.out.println("Welcome to the Money Loaning Tracker 💵");
        boolean keepGoing = true;
        String command = null;
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

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes user's account and contact list
    private void init(String name) {
        newAccount = new Account(name);
        contactList = new ContactList();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add loan");
        System.out.println("\tc -> create a new contact");
        System.out.println("\tp -> enter a payment");
        System.out.println("\tv -> view contact list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addNewLoanDetails();
        } else if (command.equals("c")) {
            createContact();
        } else if (command.equals("p")) {
            addPayment();
        } else if (command.equals("v")) {
            viewContactList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a new loan transaction
    private void addNewLoanDetails() {
        System.out.print("\nContact List: " + viewContactNames());
        if (viewContactNames().equals("No contacts to show.")) {
            System.out.println("\nYou must create a contact before adding a loan.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            Contact selectedContact = contactList.getContactByName(contactName);

            if (selectedContact == null) {
                foundNoContactToLoan(contactName);
                return;
            }

            System.out.print("Enter loan amount (positive for amount they owe, negative for amount you owe): $");
            double amount = Double.parseDouble(input.nextLine());

            System.out.println("Enter date of loan (DD/MM/YYYY): ");
            String date = input.nextLine();

            if (!(checkValidDate(date))) {
                amount = 0;
                addNewLoanDetails();
            } else {  // loan is a valid date
                selectedContact.addLoan(amount, date);
                printBalance(selectedContact);
            }
        }
    }

    private void foundNoContactToLoan(String contactName) {
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (input.nextLine().trim().equals("r")) {
            return;
        } else {
            addNewLoanDetails();
            return;
        }
    }

    public static boolean checkValidDate(String dateToValidate) {
        String dateFormat = "dd/MM/yyyy";
        // TODO: somehow the data 12/12/201030 is reading as a valid date... double check dateFormat rules
        // the problem is that max year is like 99999999999 or something, meaning this really is a valid date...

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
            System.out.println(dateToValidate + " is a valid date.");
        } catch (ParseException e) {
            System.out.println(dateToValidate + " is an invalid date. Please re-select contact and re-enter date"
                                              + " in format DD/MM/YYYY.");
            return false;
        }
        return true;
    }

    public static boolean checkValidYear(LocalDate date) {
        if (date.isAfter(LocalDate.of(2100,12,31))) {
            System.out.println(" is an invalid date. Please re-select contact and re-enter date"
                                + "in format DD/MM/YYYY");
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: creates a new contact to whom a loan or a payment can be added
    public void createContact() {
        System.out.println("Contact's name: ");
        contactName = input.nextLine();

        if (contactList.containsByName(contactName)) {
            System.out.println(contactName + " already exists as a contact. Please enter a different name.");
            return;
        } else {
            Contact newContact = new Contact(contactName);
            contactList.addContactToList(newContact);
            System.out.println("Contact " + contactName + " added to contact list.");
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
                String contactName = contact.getName();
                contactNames = contactNames + "\n" + contactName;
            }
            return contactNames;
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a new payment transaction
    public void addPayment() {
        System.out.print("\nContact List: " + viewContactNames());
        if (viewContactNames() == "No contacts to show.") {
            System.out.println("\nYou must create a contact before adding a payment.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();
            Contact selectedContact = contactList.getContactByName(contactName);

            if (selectedContact == null) {
                foundNoContactToPay(contactName);
                return;
            }

            System.out.print("Enter payment amount (positive for amount they paid you, negative for amount "
                    + "you paid them): $");
            double amount = Double.parseDouble(input.nextLine());

            initNewPayment(selectedContact, amount);
            printBalance(selectedContact);
        }
    }

    private void foundNoContactToPay(String contactName) {
        System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                + "main menu or any other key to select an existing contact.");
        if (input.nextLine().trim().equals("r")) {
            return;
        } else {
            addPayment();
            return;
        }
    }

    public void initNewPayment(Contact selectedContact, double amount) {
        Payment newPayment = new Payment(selectedContact, amount);
        selectedContact.addPaymentToTotal(amount);
        selectedContact.addPaymentToHistory(newPayment);
    }

}
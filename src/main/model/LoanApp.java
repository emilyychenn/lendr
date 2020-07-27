package model;

import java.time.LocalDate;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class LoanApp {
    private Scanner input;
    Account newAccount;
    ContactList contactList;
    String user;
    String contactName;

    public LoanApp() {
        runLoanApp();
    }

    // TODO: methods to be implemented:
    // calculateTotalBalance()

    // TODO: based off TellerApp (should this be referenced/cited somewhere??)
    private void runLoanApp() {
        System.out.println("Welcome to the Money Loaning Tracker 💵");
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        System.out.println("Enter your name: ");
        user = input.nextLine();
        init(user);
//        loadAccounts(user); // TODO!!
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
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
//    private void loadAccounts(String name) {
////        try {
////            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
////            cheq = accounts.get(0);
////            sav = accounts.get(1);
////        } catch (IOException e) {
////            init(name);
////        }
//    }

    // MODIFIES: this
    // EFFECTS: initializes account and contact list
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
//        System.out.println("\te -> edit loan details");
//        System.out.println("\ts -> save transaction history to file");
        System.out.println("\tv -> view contact list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addLoan();
        } else if (command.equals("c")) {
            createContact();
        } else if (command.equals("p")) {
            addPayment();
//        } else if (command.equals("e")) {
////            editLoan();   // to be implemented later
//        } else if (command.equals("s")) {
////            saveTransactionHistory();    // to be implemented later
        } else if (command.equals("v")) {
            viewContactList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void addLoan() {
        System.out.print("Contact List: " + viewContactNames());
        if (viewContactNames() == "No contacts to show.") {
            System.out.println("\nYou must create a contact before adding a loan.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();

            Contact selectedContact = contactList.getContactByName(contactName);
            if (selectedContact == null) {
                System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                        + "main menu or any other key to select an existing contact.");
                if (input.nextLine().trim().equals("r")) {
                    return;
                } else {
                    addLoan();
                    return;
                }
            }

            System.out.print("Enter loan amount (positive for amount they owe you, negative for amount you owe them): $");
            double amount = input.nextDouble();

            System.out.println("Enter date of loan (MM/DD/YYYY): ");
            String date = input.nextLine();
            if (checkValidDate(date)) {
                date = LocalDate.now().toString();
            } else {
                addLoan();
            }

            Loan newLoan = new Loan(amount, date);
            selectedContact.setTotalAmountOwed(amount);

            printBalance(selectedContact);
        }
    }

    public static boolean checkValidDate(String strDate) {
        if (strDate.trim().equals("")) {
            return true;
        } else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/DD/YYYY");
            sdfrmt.setLenient(false);
            /* Create Date object parse the string into date */
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is the date entered.");
            } catch (ParseException e) {
                System.out.println(strDate + " is an invalid date. Please re-select contact and re-enter date "
                                            + "in format MM/DD/YYYY.\n");
                return false;
            }
            return true;
        }
    }

    public void createContact() {
        System.out.println("Contact's name: ");
        contactName = input.nextLine();

        Contact newContact = new Contact(contactName);
        contactList.addContactToList(newContact);
        System.out.println("Contact " + contactName + " added to contact list.");
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
                double amountOwed = contact.getTotalAmountOwed();
                System.out.println(contactName + ": $ " + amountOwed + " owed");
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
        System.out.print("\nContact List: " + viewContactNames());
        if (viewContactNames() == "No contacts to show.") {
            System.out.println("\nYou must create a contact before adding a payment.");
        } else {
            System.out.println("\nEnter a contact from list above (name is not case sensitive): ");
            String contactName = input.nextLine();

            Contact selectedContact = contactList.getContactByName(contactName);
            if (selectedContact == null) {
                System.out.println(contactName + " doesn't exist in your contact list. Press 'r' to return to "
                        + "main menu or any other key to select an existing contact.");
                if (input.nextLine().trim().equals("r")) {
                    return;
                } else {
                    addPayment();
                    return;
                }
            }

            System.out.print("Enter payment amount (positive for amount they paid you, negative for amount "
                    + "you paid them): $");
            double amount = input.nextDouble();

            Payment newPayment = new Payment(selectedContact, amount);
            selectedContact.addPaymentToTotal(amount);
            selectedContact.addPaymentToHistory(newPayment);

            printBalance(selectedContact);
        }
    }

}

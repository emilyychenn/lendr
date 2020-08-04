package model;

/**
 *  Represents one of the user's contacts to whom the user owes money to or who owe money to the user.
 */

public class Contact {
    private String name;
    private double contactBalance; // positive if they owe me, negative if I owe them

    // EFFECTS: creates a default constructor for JSON reader
    public Contact() {
    }

    // EFFECTS: constructs a contact with given name and default values of $0 owed, an empty loanList and an empty
    //          payment history
    public Contact(String name) {
        this.name = name;
        this.contactBalance = 0.00; // positive if they owe me, negative if I owe them
    }

    // EFFECTS: returns name of contact
    public String getName() {
        return this.name;
    }

    // EFFECTS: sets name of contact
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns total amount owed to/from contact: value is positive if they owe the user and negative if the
    //          user owes them
    public double getContactBalance() {
        return this.contactBalance;
    }

    // EFFECTS: sets contact's balance: value is positive if they owe the user and negative if the user owes them
    public void setContactBalance(double amount) {
        this.contactBalance = amount;
    }

    // MODIFIES: contactBalance
    // EFFECTS: adds given amount to contactBalance
    public void addAmountToBalance(double amount) {
        contactBalance = contactBalance + amount;
    }

    // MODIFIES: contactBalance
    // EFFECTS: removes given amount from contactBalance
    public void removeAmountFromBalance(double amount) {
        contactBalance = contactBalance - amount;
    }
}

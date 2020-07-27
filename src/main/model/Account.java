package model;

/**
 *  Represents the user's account.
 */

public class Account {
    // to be implemented later: username, password, currency
    private String name;
    private Double balance;
    private ContactList contactList;

    // MODIFIES: this
    // EFFECTS: constructs a new account with 0 balance and an empty contact list
    public Account(String name) {
        this.name = name;
        this.balance = 0.00;
        this.contactList = new ContactList();
    }

    // EFFECTS: return's account owner's (user's) name
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns contact list
    public ContactList getContactList() {
        return this.contactList;
    }

    // EFFECTS: returns account overall balance
    public Double getBalance() {
        return this.balance;
    }
}

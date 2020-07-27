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

    public ContactList getContactList() {
        return contactList;
    }

    public Double getBalance() {
        return balance;
    }
}

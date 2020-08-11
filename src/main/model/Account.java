package model;

/**
 *  Represents the user's account.
 */

public class Account {
    // to be implemented later: balance, username, password, currency?
    private String name;
    private ContactList contactList;
    private TransactionHistory transactionHistory;

    // EFFECTS: constructs a default constructor
    public Account() {
        this.name = null;
        this.contactList = new ContactList();
        this.transactionHistory = new TransactionHistory();
    }

    // MODIFIES: this
    // EFFECTS: constructs a new account with an empty contact list
    public Account(String name) {
        this.name = name;
        this.contactList = new ContactList();
        this.transactionHistory = new TransactionHistory();
    }

    // EFFECTS: return's account owner's (user's) name
    public String getName() {
        return this.name;
    }

    // EFFECTS: return's account owner's (user's) name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns contact list
    public ContactList getContactList() {
        return this.contactList;
    }

    // EFFECTS: returns transaction history
    public TransactionHistory getTransactionHistory() {
        return this.transactionHistory;
    }

    // MODIFIES: this (contactList)
    // EFFECTS: sets contact list for account
    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    // MODIFIES: this (transactionHistory)
    // EFFECTS: sets transaction history
    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    // REQUIRES: transaction is already in transaction history
    // MODIFIES: transactionHistory and totalAmountOwed
    // EFFECTS: removes a transaction from history and removes that amount from the totalAmountOwed
    public void removeTransactionFromHistory(Transaction transaction) {
        transactionHistory.removeTransaction(transaction);
    }

    // MODIFIES: this
    // EFFECTS: adds a transaction to the transaction history
    public void addTransactionToHistory(Transaction transaction) {
        transactionHistory.addTransaction(transaction);
    }

}

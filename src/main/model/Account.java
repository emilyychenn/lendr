package model;

import persistence.DataAccessor;

/**
 *  Represents the user's account.
 */

public class Account {
    // to be implemented later: username, password, currency
    private String name;
//    private Double balance; // to be implemented later (right now there is no need for this since there is no option
//                               to view the account's overall balance yet)
    private ContactList contactList;
    private TransactionHistory transactionHistory;

    // MODIFIES: this
    // EFFECTS: constructs a new account with 0 balance and an empty contact list
    public Account(String name) {
        this.name = name;
//        this.balance = 0.00;
        this.contactList = new ContactList();
        this.transactionHistory = new TransactionHistory();
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
//    public Double getBalance() {
//        return this.balance;
//    }

    // EFFECTS: returns transaction history
    public TransactionHistory getTransactionHistory() {
        return this.transactionHistory;
    }

    // MODIFIES: this (balance)
    // EFFECTS: sets balance for account
//    public void setBalance(double amount) {
//        this.balance = amount;
//    }

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

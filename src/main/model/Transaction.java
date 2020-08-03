package model;

import java.util.UUID;

// TODO: combine Loan and Payment classes into this one transaction class!! Note: need contact as a field
public class Transaction {
    private double amount;
    private Contact contact;
    private String dateOfTransaction;
    private String transactionID;

    // EFFECTS: creates a new transaction
    public Transaction(double amount, Contact contact, String date) {
        this.amount = amount;
        this.contact = contact;
        this.dateOfTransaction = date;
        this.transactionID = UUID.randomUUID().toString();
    }

    // EFFECTS: returns amount of a transaction
    public double getAmount() {
        return amount;
    }

    // REQUIRES: positive amount for amount paid to you, negative for amount you paid to someone else
    // MODIFIES: amount
    // EFFECTS: sets amount of transaction to given amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // EFFECTS: returns contact associated with a transaction
    public Contact getContact() {
        return contact;
    }

    // MODIFIES: this (contact)
    // EFFECTS: changes contact to given contact
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    // EFFECTS: returns date of transaction
    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    // REQUIRES: valid date on or before Dec 31, 2100
    // MODIFIES: date
    // EFFECTS: sets date of transaction to given date
    public void setDateOfTransaction(String date) {
        this.dateOfTransaction = date;
    }

    // EFFECTS: returns unique transaction ID
    public String getTransactionID() {
        return transactionID;
    }
}

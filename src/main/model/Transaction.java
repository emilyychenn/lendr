package model;

import java.util.UUID;

// TODO: combine Loan and Payment classes into this one transaction class!! Note: need contact as a field
public class Transaction {
    private double amount;
    private Contact contact;
    private String dateOfTransaction;
    private String transactionID;

    public Transaction(double amount, Contact contact, String date) {
        this.amount = amount;
        this.contact = contact;
        this.dateOfTransaction = date;
        this.transactionID = UUID.randomUUID().toString();
    }

    public double getAmount() {
        return amount;
    }

    // REQUIRES: positive amount for amount paid to you, negative for amount you paid to someone else
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    // REQUIRES: valid date on or before Dec 31, 2100
    public void setDateOfTransaction(String date) {
        this.dateOfTransaction = date;
    }

    public String getTransactionID() {
        return transactionID;
    }
}

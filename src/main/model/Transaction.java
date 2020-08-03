package model;

// TODO: combine Loan and Payment classes into this one transaction class!! Note: need contact as a field
public class Transaction {
    private double amount;
    private Contact contact;
    private String dateOfTransaction;

    public Transaction(double amount, Contact contact, String date) {
        this.amount = amount;
        this.contact = contact;
        this.dateOfTransaction = date;
    }

    public Contact getContact() {
        return contact;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }
}

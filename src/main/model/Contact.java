package model;

/**
 *  Represents one of the user's contacts to whom the user owes money to or who owe money to the user.
 */

public class Contact {
    private String name;
    private double contactBalance; // positive if they owe me, negative if I owe them
//    private TransactionHistory transactionHistory = new TransactionHistory();

    // EFFECTS: constructs a contact with given name and default values of $0 owed, an empty loanList and an empty
    //          payment history
    public Contact(String name) {
        this.name = name;
        this.contactBalance = 0.00; // positive if they owe me, negative if I owe them
//        this.transactionHistory = transactionHistory.getTransactionsByContactName(name);
    }

    // EFFECTS: returns name of contact
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns total amount owed to/from contact: value is positive if they owe the user and negative if the
    //          user owes them
    public double getContactBalance() {
        return this.contactBalance;
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

    // EFFECTS: returns the transaction history (both payments and loans) between the user and the contact
//    public TransactionHistory getTransactionHistory() {
//        return this.transactionHistory;
//    }

    // REQUIRES: positive value for amount someone else pays the user, negative for amount user pays someone else
    // MODIFIES: this (totalAmountOwed)
    // EFFECTS: subtracts amount paid from amount owed
//    public double addTransactionToAmountOwed(double amount) {
//        totalAmountOwed = totalAmountOwed - amount;
//        return totalAmountOwed;
//    }

    // MODIFIES: this
    // EFFECTS: adds a transaction to the transaction history
//    public void addTransactionToHistory(Transaction transaction) {
//        transactionHistory.addTransaction(transaction);
//        totalAmountOwed = totalAmountOwed - transaction.getAmount();
//    }

    // MODIFIES: this
    // EFFECTS: changes total amount to reflect new loan, creates a new loan object, and adds it to the list of transactions
//    public void addTransaction(double amount, String date) {
//        addTransactionToAmountOwed(amount);
//        Transaction newTransaction = new Transaction(amount, this, date);
//        addTransactionToList(newTransaction);
//    }

    // MODIFIES: this
    // EFFECTS: adds a transaction to the list of transactions
//    public void addTransactionToList(Transaction transaction) {
//        transactionHistory.addTransaction(transaction);
//    }
}

package model;

import java.util.LinkedList;

/**
 *  Represents the a list of transactions.
 */

// LIST OF ALL USER'S TRANSACTIONS (not sorted by contact!!)
public class TransactionHistory {
    LinkedList<Transaction> transactions;

    // EFFECTS: constructs a transaction history
    public TransactionHistory() {
        this.transactions = new LinkedList<>();
    }

    // EFFECTS: returns transaction history
    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    // EFFECTS: sets transaction history for reading from JSON file
    public void setTransactions(LinkedList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // MODIFIES: this
    // EFFECTS: adds given transaction to the list of transactions (i.e. the transaction history)
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // MODIFIES: this
    // EFFECTS: removes a given transaction from the list of transactions (i.e. the transaction history)
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    // MODIFIES: this
    // EFFECTS: creates a transaction list that displays transactions for a given contact
    public TransactionHistory getTransactionsByContactName(String contactName) {
        TransactionHistory transactionsList = new TransactionHistory();
        for (Transaction t : transactions) {
            if (t.getContact().getName().equalsIgnoreCase(contactName)) {
                transactionsList.addTransaction(t);
            }
        }
        return transactionsList;
    }

    // EFFECTS: returns the transaction corresponding to the unique transaction ID
    public Transaction getTransactionByID(String transactionID) {
        for (Transaction t : transactions) {
            if (t.getTransactionID().equals(transactionID)) {
                return t;
            }
        }
        return null;
    }

    // EFFECTS: returns number of transactions in transaction history
    public int size() {
        return transactions.size();
    }

    // EFFECTS: returns transaction at given index in transaction history
    public Transaction getFromIndex(int i) {
        return transactions.get(i);
    }

    // EFFECTS: prints transactions in given transaction history
    public String printTransactionHistory() {
        String printedTransactions = "";
        if (transactions.size() == 0) {
            return "No transactions to show.";
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction t = transactions.get(i);
                printedTransactions = printedTransactions.concat("\nTransaction ID: " + t.getTransactionID()
                        + ", Contact: " + t.getContact().getName() + ", Amount: $" + t.getAmount() + ", Date: "
                        + t.getDateOfTransaction());
            }
            return printedTransactions;
        }
    }
}

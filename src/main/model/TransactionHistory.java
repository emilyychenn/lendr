package model;

import java.util.LinkedList;

// LIST OF ALL USER'S TRANSACTIONS (not sorted by contact!!)
public class TransactionHistory {
    LinkedList<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new LinkedList<>();
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    // MODIFIES: this
    // EFFECTS: adds given transaction to the list of transactions (i.e. the transaction history)
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
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


    // TODO: methods to implement
    // - get transactions by date range
}

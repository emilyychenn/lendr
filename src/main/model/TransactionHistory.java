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

    public Transaction getTransactionByID(String transactionID) {
        for (Transaction t : transactions) {
            if (t.getTransactionID().equals(transactionID)) {
                return t;
            }
        }
        return null;
    }

    public int size() {
        return transactions.size();
    }

    public Transaction getFromIndex(int i) {
        return transactions.get(i);
    }

    public String printTransactionHistory(TransactionHistory th) {
        String printedTransactions = "";
        if (th.size() == 0) {
            return "No transactions to show.";
        } else {
            for (int i = 0; i < th.size(); i++) {
                Transaction t = th.getFromIndex(i);
                printedTransactions = printedTransactions.concat("\nTransaction ID: " + t.getTransactionID() + ", Contact: "
                        + t.getContact().getName() + ", Amount: $" + t.getAmount() + ", Date: " + t.getDateOfTransaction());
            }
            return printedTransactions;
        }
    }

    // TODO: methods to implement
    // - get transactions by date range
}

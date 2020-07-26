package model;

import java.util.LinkedList;

public class TransactionHistory {
    private LinkedList<Transaction> transactionHistory;
    private User recipient;
    private User payor;

    public TransactionHistory(User recipient, User payor) {
        this.recipient = recipient;
        this.payor = payor;
        this.transactionHistory = new LinkedList<>();
    }

    public void addTransactionToHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}

package model;

import java.util.LinkedList;

public class PaymentHistory {
    private LinkedList<Payment> paymentHistory;
    private User recipient;
    private User payor;

    public PaymentHistory(User recipient, User payor) {
        this.recipient = recipient;
        this.payor = payor;
        this.paymentHistory = new LinkedList<>();
    }

    public void addTransactionToHistory(Payment payment) {
        paymentHistory.add(payment);
    }
}

package model;

import java.util.LinkedList;

/**
 *  Represents the complete payment history (i.e. the payments in a sorted list).
 */

public class PaymentHistory {
    private LinkedList<Payment> payments;
    private Contact contact;

    public PaymentHistory(Contact contact) {
        this.contact = contact;
        this.payments = new LinkedList<>();
    }

    public void addPaymentToHistory(Payment payment) {
        payments.add(payment);
    }
}

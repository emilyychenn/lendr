package model;

import java.util.LinkedList;

/**
 *  Represents the complete payment history (i.e. the payments in a sorted list).
 */

public class PaymentHistory {
    private LinkedList<Payment> payments;
    private Contact contact;

    // EFFECTS: constructs a payment history between the given contact and the user
    public PaymentHistory(Contact contact) {
        this.contact = contact;
        this.payments = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given payment to the list of payments (i.e. the payment history)
    public void addPaymentToHistory(Payment payment) {
        payments.add(payment);
    }

    public int getNumPayments() {
        return payments.size();
    }
}

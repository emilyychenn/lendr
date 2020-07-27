package model;

import java.time.LocalDate;

/**
 *  Represents a payment made when someone is paying back all or part of a loan. Could also pay more than the loan.
 */

public class Payment {
    private LocalDate paymentDate;
    private Contact contact;
    private Double amount;

    // EFFECTS: constructs a new payment and sets the payment date to today's date as a default
    public Payment(Contact contact, Double amount) {
        this.paymentDate = LocalDate.now();
        this.contact = contact;
        this.amount = amount; // +'ve amount is payed to me, -'ve payed to contact
    }
}

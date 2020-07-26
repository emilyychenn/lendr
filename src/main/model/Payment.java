package model;

import java.time.LocalDate;

public class Payment {
    private LocalDate paymentDate;
    private Contact contact;
    private Double amount;

    public Payment(User contact, Double amount) {
        this.paymentDate = LocalDate.now(); // default payment date is today's date
        this.contact = contact;
        this.amount = amount; // +'ve amount is payed to me, -'ve payed to contact
    }
}

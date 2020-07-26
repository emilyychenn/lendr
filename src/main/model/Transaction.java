package model;

import java.time.LocalDate;

public class Transaction {
    private LocalDate transactionDate;
    private Contact contact;
    private Double amount;

    public Transaction(User contact, Double amount) {
        this.transactionDate = LocalDate.now();
        this.contact = contact;
        this.amount = amount; // +'ve amount is payed to me, -'ve payed to contact
    }
}

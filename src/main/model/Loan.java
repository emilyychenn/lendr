package model;

import java.time.LocalDate;

public class Loan {
    private LocalDate dateOwed;
    private LocalDate dateDue;
    private Double totalAmount; // TODO: change to currency later!
    private Currency amountRemaining;
    private Currency amountPaid;

    // EFFECTS: constructs a loan given amount owed and date owed; date due is set later
    public Loan(Double amountOwed, LocalDate dateOwed) {
        this.totalAmount = amountOwed;
        this.dateOwed = dateOwed;
        this.dateDue = null;
    }


    // TODO: methods to be implemented:
    // editDetails() // to edit loan details
}

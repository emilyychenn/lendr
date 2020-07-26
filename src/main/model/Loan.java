package model;

import java.time.LocalDate;

public class Loan {
    private String dateOwed; // TODO: change to LocalDate type
//    private LocalDate dateDue; // optional field for later
    private double totalAmount; // TODO: change to currency later!
    private double amountRemaining;
    private double amountPaid;

    // EFFECTS: constructs a loan given amount owed and date owed; date due is set later
    public Loan(Double amountOwed, String dateOwed) {
        this.totalAmount = amountOwed;
        this.dateOwed = dateOwed;
//        this.dateDue = null; // to be implemented later as another feature
    }


    // TODO: methods to be implemented:
    // editDetails() // to edit loan details
}

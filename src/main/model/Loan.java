package model;

/**
 *  Represents a loan: money lent to/from someone that can get paid back in several payments if necessary.
 */

public class Loan {
    private double totalAmount;
    private String dateOwed;

    // EFFECTS: constructs a loan given amount owed and date owed
    public Loan(Double amountOwed, String dateOwed) {
        this.totalAmount = amountOwed;
        this.dateOwed = dateOwed;
    }
}

package model;

/**
 *  Represents a loan: money lent to/from someone that can get paid back in several payments if necessary.
 */

public class Loan {
    private double totalAmountOwed;
    private String dateOwed;

    // EFFECTS: constructs a loan given amount owed and date owed
    public Loan(Double amountOwed, String dateOwed) {
        this.totalAmountOwed = amountOwed;
        this.dateOwed = dateOwed;
    }

    // EFFECTS: returns total sum of loan (does not take payments into account)
    public double getTotalAmountOwed() {
        return this.totalAmountOwed;
    }

    // EFFECTS: returns date of loan
    public String getDateOwed() {
        return this.dateOwed;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Represents a list of loans.
 */

public class LoanList {
    List<Loan> loans;

    // EFFECTS: constructs a new list of loans
    public LoanList() {
        loans = new ArrayList<>();
    }

    // EFFECTS: returns size of list of loans
    public int getSize() {
        return loans.size();
    }

    // MODIFIES: this
    // EFFECTS: adds given payment to the list of payments (i.e. the payment history)
    public void addLoanToList(Loan loan) {
        loans.add(loan);
    }
}

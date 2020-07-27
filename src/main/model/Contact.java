package model;

/**
 *  Represents one of the user's contacts to whom the user owes money to or who owe money to the user.
 */

public class Contact {
    private String name;
    private double totalAmountOwed; // positive if they owe me, negative if I owe them
    private LoanList loanList;
    private PaymentHistory paymentHistory;

    // EFFECTS: constructs a contact with given name and default values of $0 owed, an empty loanList and an empty
    //          payment history
    public Contact(String name) {
        this.name = name;
        this.totalAmountOwed = 0.00;
        this.loanList = new LoanList();
        this.paymentHistory = new PaymentHistory(this);
    }

    // EFFECTS: returns name of contact
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns total amount owed to/from contact: value is positive if they owe the user and negative if the
    //          user owes them
    public double getTotalAmountOwed() {
        return this.totalAmountOwed;
    }

    // EFFECTS: returns a list of all the loans between the user and the contact
    public LoanList getLoanList() {
        return this.loanList;
    }

    // EFFECTS: returns the payment history between the user and the contact
    public PaymentHistory getPaymentHistory() {
        return this.paymentHistory;
    }

    // REQUIRES: amount owed from user to someone else is negative, amount owed to user from someone else is positive
    // MODIFIES: this (totalAmountOwed)
    // EFFECTS: changes amount owed after a loan is added
    public double addLoanToAmountOwed(double amount) {
        totalAmountOwed = totalAmountOwed + amount;
        return totalAmountOwed;
    }

    // REQUIRES: positive value for amount someone else pays the user, negative for amount user pays someone else
    // MODIFIES: this (totalAmountOwed)
    // EFFECTS: subtract amount paid from amount owed
    public double addPaymentToTotal(double amount) {
        totalAmountOwed = totalAmountOwed - amount;
        return totalAmountOwed;
    }

    // MODIFIES: this
    // EFFECTS: adds a payment to the payment history
    public void addPaymentToHistory(Payment payment) {
        paymentHistory.addPaymentToHistory(payment);
    }

    // MODIFIES: this
    // EFFECTS: changes total amount to reflect new loan, creates a new loan object, and adds it to the list of loans
    public void addLoan(double amount, String date) {
        addLoanToAmountOwed(amount);
        Loan newLoan = new Loan(amount, date);
        addLoanToList(newLoan);
    }

    // MODIFIES: this
    // EFFECTS: adds a loan to the list of loans
    public void addLoanToList(Loan loan) {
        loanList.addLoanToList(loan);
    }
}

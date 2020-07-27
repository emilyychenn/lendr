package model;

/**
 *  Represents one of the user's contacts to whom the user owes money to or who owe money to the user.
 */

public class Contact {
    private String name;
    private double totalAmountOwed; // positive if they owe me, negative if I owe them; TODO: change to currency later!
    private LoanList loanList;
    private boolean user; // true if they have an account, false if not
    private Account account; // account associated with user given that boolean user is true, null if false
    private PaymentHistory paymentHistory;

    public Contact(String name) {
        this.name = name;
        this.totalAmountOwed = 0.00;
        this.loanList = null;
        this.user = false;
        this.account = null;
        this.paymentHistory = new PaymentHistory(this);
    }

    public String getName() {
        return this.name;
    }

    public double getTotalAmountOwed() {
        return this.totalAmountOwed;
    }

    public double setTotalAmountOwed(double amount) {
        totalAmountOwed = totalAmountOwed + amount;
        return totalAmountOwed;
    }

    public double addPaymentToTotal(double amount) {
        totalAmountOwed = totalAmountOwed - amount;
        return totalAmountOwed;
    }

    public void addPaymentToHistory(Payment payment) {
        paymentHistory.addPaymentToHistory(payment);
    }
}

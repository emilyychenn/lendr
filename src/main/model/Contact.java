package model;

public class Contact {
    private String name;
    private long phoneNum;
    private double totalAmountOwed; // positive if they owe me, negative if I owe them; TODO: change to currency later!
    private boolean user; // true if they have an account, false if not
    private Account account; // account associated with user given that boolean user is true, null if false

    public Contact(String name, long phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.totalAmountOwed = 0.00;
        this.user = false;
        this.account = null;
    }

    public String getName() {
        return this.name;
    }

    public long getPhoneNum() {
        return this.phoneNum;
    }

    public double getTotalAmountOwed() {
        return this.totalAmountOwed;
    }

    // TODO: METHODS TO IMPLEMENT:
    // viewTransactionHistory()
    // addLoan()

}

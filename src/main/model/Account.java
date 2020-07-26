package model;

public class Account {
    // to be implemented later: username, password, currency

    private String name;
    private Double balance;
    private ContactList contactList;

    public Account(String name) {
        this.name = name;
        this.balance = 0.00;
        this.contactList = null;
    }

    public ContactList getContactList() {
        return contactList;
    }

    public Double getBalance() {
        return balance;
    }

    // TODO: methods to be implemented:
    // viewContactList();
    // viewTotalBalance();

}

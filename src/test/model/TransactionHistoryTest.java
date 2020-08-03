package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the TransactionHistory class.
 */

public class TransactionHistoryTest {
    TransactionHistory transactionHistory;
    Contact testContact;

    @BeforeEach
    public void setUp() {
        transactionHistory = new TransactionHistory();
        testContact = new Contact("Emily");
    }

    @Test
    public void testSetTransactions() {
        LinkedList<Transaction> transactions = new LinkedList<Transaction>();
        transactions.add(new Transaction(100, testContact, "12/12/2020"));
        transactions.add(new Transaction(-250, testContact, "10/06/2020"));
        TransactionHistory transactionHistory2 = new TransactionHistory();
        transactionHistory2.setTransactions(transactions);
        assertEquals(transactions, transactionHistory2.getTransactions());
    }

    @Test
    public void testAddTransaction() {
        transactionHistory.addTransaction(new Transaction(100, testContact, "12/12/2020"));
        assertEquals(1, transactionHistory.size());
        transactionHistory.addTransaction(new Transaction(-250, testContact, "10/06/2020"));
        assertEquals(2, transactionHistory.size());
    }

    @Test
    public void testRemoveTransaction() {
        Transaction transaction1 = new Transaction(-250, testContact, "10/06/2020");
        Transaction transaction2 = new Transaction(-250, testContact, "01/01/2020");
        Transaction transaction3 = new Transaction(-250, testContact, "02/12/2020");
        transactionHistory.addTransaction(transaction1);
        transactionHistory.addTransaction(transaction2);
        transactionHistory.addTransaction(transaction3);
        transactionHistory.removeTransaction(transaction1);
        assertEquals(2, transactionHistory.size());
        transactionHistory.removeTransaction(transaction2);
        assertEquals(1, transactionHistory.size());
        transactionHistory.removeTransaction(transaction3);
        assertEquals(0, transactionHistory.size());
    }

    @Test
    public void testGetTransactionsByContactName() {
        Contact contact2 = new Contact("Felix");
        Contact contact3 = new Contact("Arthur");

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, testContact, "11/04/2019");
        Transaction transaction3 = new Transaction(30, contact2, "12/12/2009");
        Transaction transaction4 = new Transaction(-1000, contact3, "10/12/2019");
        Transaction transaction5 = new Transaction(35.50, testContact, "11/11/2019");
        Transaction transaction6 = new Transaction(38, contact2, "10/11/2019");

        transactionHistory.addTransaction(transaction1);
        transactionHistory.addTransaction(transaction2);
        transactionHistory.addTransaction(transaction3);
        transactionHistory.addTransaction(transaction4);
        transactionHistory.addTransaction(transaction5);
        transactionHistory.addTransaction(transaction6);

        assertEquals(3, transactionHistory.getTransactionsByContactName(testContact.getName()).size());
        assertEquals(2, transactionHistory.getTransactionsByContactName("Felix").size());
        assertEquals(1, transactionHistory.getTransactionsByContactName("Arthur").size());

    }

    @Test
    public void testGetTransactionByID() {
        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        transaction1.setTransactionID("9b8f18c1-be26-4f26-ab70-0a7eb6cf324d");
        transactionHistory.addTransaction(transaction1);
        assertEquals(transaction1, transactionHistory.getTransactionByID("9b8f18c1-be26-4f26-ab70-0a7eb6cf324d"));
        assertEquals(null, transactionHistory.getTransactionByID("yummy Coconut 12345"));
    }

    @Test
    public void testGetFromIndex() {
        Contact contact2 = new Contact("Felix");
        Contact contact3 = new Contact("Arthur");

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, testContact, "11/04/2019");
        Transaction transaction3 = new Transaction(30, contact2, "12/12/2009");
        Transaction transaction4 = new Transaction(-1000, contact3, "10/12/2019");
        Transaction transaction5 = new Transaction(35.50, testContact, "11/11/2019");

        transactionHistory.addTransaction(transaction1);
        transactionHistory.addTransaction(transaction2);
        transactionHistory.addTransaction(transaction3);
        transactionHistory.addTransaction(transaction4);
        transactionHistory.addTransaction(transaction5);

        assertEquals(transaction1, transactionHistory.getFromIndex(0));
        assertEquals(transaction2, transactionHistory.getFromIndex(1));
        assertEquals(transaction3, transactionHistory.getFromIndex(2));
        assertEquals(transaction4, transactionHistory.getFromIndex(3));
        assertEquals(transaction5, transactionHistory.getFromIndex(4));
    }

    @Test
    public void testPrintTransactionHistory() {
        assertEquals("No transactions to show.", transactionHistory.printTransactionHistory());
        Contact contact2 = new Contact("Felix");
        Contact contact3 = new Contact("Arthur");

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, contact2, "11/04/2019");

        transactionHistory.addTransaction(transaction1);
        transactionHistory.addTransaction(transaction2);

        String printedTransactions = "";
        for (int i = 0; i < transactionHistory.size(); i++) {
            Transaction t = transactionHistory.getFromIndex(i);
            printedTransactions = printedTransactions.concat("\nTransaction ID: " + t.getTransactionID() + ", Contact: "
                    + t.getContact().getName() + ", Amount: $" + t.getAmount() + ", Date: " + t.getDateOfTransaction());
        }
        assertEquals(printedTransactions, transactionHistory.printTransactionHistory());
        // Note: this is difficult to test for since the transaction ID changes every time, but this should test as well
    }

}

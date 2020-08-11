package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the Account class.
 */

public class AccountTest {
    Account testAccount;
    Account testAccount2;

    @BeforeEach
    public void setUp() {
        testAccount = new Account("Felix");
        testAccount2 = new Account();
    }

    @Test
    public void testDefaultConstructor() {
        System.out.println("Contact list: " + testAccount2.getContactList());
        assertTrue(testAccount2.getContactList().countNumContacts() == 0);
        assertTrue(testAccount2.getTransactionHistory().size() == 0);
    }

    @Test
    public void testAccount() {
        assertEquals("Felix", testAccount.getName());
        assertEquals(0, testAccount.getContactList().countNumContacts());
        assertEquals(0, testAccount.getTransactionHistory().size());
    }

    @Test
    public void testSetName() {
        testAccount.setName("PalmTree");
        assertEquals("PalmTree", testAccount.getName());
        testAccount.setName("Arthur");
        assertEquals("Arthur", testAccount.getName());
    }

    @Test
    public void testSetContactList() {
        ContactList testList = new ContactList();
        Contact contact1 = new Contact("Emily");
        Contact contact2 = new Contact("Arthur");
        Contact contact3 = new Contact("Diane");

        testList.addContactToList(contact1);
        testList.addContactToList(contact2);
        testList.addContactToList(contact3);

        testAccount.setContactList(testList);
        assertEquals(testList, testAccount.getContactList());
    }


    @Test
    public void testSetTransactionHistory() {
        Contact testContact = new Contact("Kate");
        Contact testContact2 = new Contact("David");

        TransactionHistory transactions = new TransactionHistory();

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, testContact, "11/04/2019");
        Transaction transaction3 = new Transaction(30, testContact2, "12/12/2009");

        transactions.addTransaction(transaction1);
        transactions.addTransaction(transaction2);
        transactions.addTransaction(transaction3);

        testAccount.setTransactionHistory(transactions);
        assertEquals(transactions, testAccount.getTransactionHistory());
    }

    @Test
    public void testRemoveTransactionFromHistory() {
        Contact testContact = new Contact("Kate");
        Contact testContact2 = new Contact("David");

        TransactionHistory transactions = new TransactionHistory();

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, testContact, "11/04/2019");
        Transaction transaction3 = new Transaction(30, testContact2, "12/12/2009");

        transactions.addTransaction(transaction1);
        transactions.addTransaction(transaction2);
        transactions.addTransaction(transaction3);

        testAccount.setTransactionHistory(transactions);
        testAccount.removeTransactionFromHistory(transaction1);
        assertEquals(2, testAccount.getTransactionHistory().size());
        testAccount.removeTransactionFromHistory(transaction3);
        assertEquals(1, testAccount.getTransactionHistory().size());
        testAccount.removeTransactionFromHistory(transaction2);
        assertEquals(0, testAccount.getTransactionHistory().size());
    }

    @Test
    public void testAddTransactionToHistory() {
        Contact testContact = new Contact("Kate");
        Contact testContact2 = new Contact("David");

        Transaction transaction1 = new Transaction(100, testContact, "12/12/2019");
        Transaction transaction2 = new Transaction(250, testContact, "11/04/2019");
        Transaction transaction3 = new Transaction(30, testContact2, "12/12/2009");

        assertEquals(0, testAccount.getTransactionHistory().size());
        testAccount.addTransactionToHistory(transaction1);
        assertEquals(1, testAccount.getTransactionHistory().size());
        testAccount.addTransactionToHistory(transaction2);
        assertEquals(2, testAccount.getTransactionHistory().size());
        testAccount.addTransactionToHistory(transaction3);
        assertEquals(3, testAccount.getTransactionHistory().size());
    }
}

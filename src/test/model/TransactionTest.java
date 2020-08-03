package model;

import model.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the Transaction class.
 */

public class TransactionTest {
    Transaction testTransaction;
    Contact testContact;
    String date = "02/08/2020";

    @BeforeEach
    public void setUp() {
        testContact = new Contact("Test Contact");
        testTransaction = new Transaction(-100.0, testContact, date);
    }

    @Test
    public void testConstructor() {
        assertEquals(-100.0, testTransaction.getAmount());
        assertEquals(date, testTransaction.getDateOfTransaction());
        assertEquals(testContact, testTransaction.getContact());
    }

    @Test
    public void testSetAmount() {
        assertEquals(-100.0, testTransaction.getAmount());
        testTransaction.setAmount(200.0);
        assertEquals(200.0, testTransaction.getAmount());
    }

    @Test
    public void testSetContact() {
        assertEquals(testContact, testTransaction.getContact());
        Contact newContact = new Contact("Felix");
        testTransaction.setContact(newContact);
        Contact newContact2 = new Contact("Arthur");
        testTransaction.setContact(newContact2);
    }

    @Test
    public void testSetDateOfTransaction() {
        // requires a valid date
        assertEquals(date, testTransaction.getDateOfTransaction());
        testTransaction.setDateOfTransaction("12/12/2020");
        assertEquals("12/12/2020", testTransaction.getDateOfTransaction());
    }

    @Test
    public void testGetTransactionID() {
        Transaction testTransaction2 = new Transaction(200.0, testContact, date);
        Transaction testTransaction3 = new Transaction(200.0, testContact, "12/12/2020");
        assertFalse(testTransaction.getTransactionID().equals(testTransaction2.getTransactionID()));
        assertFalse(testTransaction2.getTransactionID().equals(testTransaction3.getTransactionID()));
        assertFalse(testTransaction.getTransactionID().equals(testTransaction3.getTransactionID()));
    }


}

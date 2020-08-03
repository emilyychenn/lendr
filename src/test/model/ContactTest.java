package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the Contact class.
 */

class ContactTest {
    Contact newContact;
    Contact newContact2;
    Transaction testTransaction;

    @BeforeEach
    public void setUp() {
        newContact = new Contact("Test Name");
        newContact2 = new Contact();
    }

    @Test
    public void testDefaultConstructor() {
        assertTrue(newContact2.getName() == null);
    }

    @Test
    public void testContact() {
        assertEquals("Test Name", newContact.getName());
        assertEquals(0.0, newContact.getContactBalance());
    }

    @Test
    public void testSetName() {
        newContact.setName("Felix");
        assertEquals("Felix", newContact.getName());
        newContact.setName("Felix Grund");
        assertEquals("Felix Grund", newContact.getName());
    }

    @Test
    public void testSetContactBalance() {
        newContact.setContactBalance(100);
        assertEquals(100.0, newContact.getContactBalance());
        newContact.setContactBalance(-5000);
        assertEquals(-5000.0, newContact.getContactBalance());
    }

    @Test
    public void testAddAmountToBalance() {
        newContact.addAmountToBalance(100);
        assertEquals(100.0, newContact.getContactBalance());
        newContact.addAmountToBalance(250);
        assertEquals(350.0, newContact.getContactBalance());
    }

    @Test
    public void testRemoveAmountFromBalance() {
        newContact.removeAmountFromBalance(100);
        assertEquals(-100.0, newContact.getContactBalance());
        newContact.removeAmountFromBalance(-450);
        assertEquals(350.0, newContact.getContactBalance());
    }
}
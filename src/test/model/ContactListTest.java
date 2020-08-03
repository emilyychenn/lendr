package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the ContactList class.
 */

public class ContactListTest {
    ContactList contactList;
    Contact contact1;
    Contact contact2;
    Contact contact3;

    @BeforeEach
    public void setUp() {
        contactList = new ContactList();
        contact1 = new Contact("Felix");
        contact2 = new Contact("Arthur");
        contact3 = new Contact("Nick");
        contactList.addContactToList(contact1);
        contactList.addContactToList(contact2);
        contactList.addContactToList(contact3);
    }

    @Test
    public void testGetContactByName() {
        assertEquals(contact3, contactList.getContactByName("Nick"));
        assertEquals(contact1, contactList.getContactByName("Felix"));
        assertEquals(contact2, contactList.getContactByName("Arthur"));
        assertEquals(null, contactList.getContactByName("Emily"));
    }

    @Test
    public void testSetContacts() {
        List<Contact> contactList2 = new ArrayList<>();
        contact1 = new Contact("Felix");
        contact2 = new Contact("Arthur");
        contact3 = new Contact("Nick");
        contactList2.add(contact1);
        contactList2.add(contact2);
        contactList2.add(contact3);

        contactList.setContacts(contactList2);
        assertTrue(contactList.getContacts().equals(contactList2));
    }

    @Test
    public void testAddContactToList() {
        Contact contact4 = new Contact("Palm Tree");
        Contact contact5 = new Contact("CPSC-210");
        contactList.addContactToList(contact4);
        assertEquals(4, contactList.countNumContacts());
        contactList.addContactToList(contact5);
        assertEquals(5, contactList.countNumContacts());
    }

    @Test
    public void testGetContactFromIndex() {
        assertEquals(contact2, contactList.getContactFromIndex(1));
        assertEquals(contact1, contactList.getContactFromIndex(0));
        assertEquals(contact3, contactList.getContactFromIndex(2));
    }

    @Test
    public void testContainsByName() {
        assertTrue(contactList.containsByName("Felix"));
        assertFalse(contactList.containsByName("Emily"));
        assertTrue(contactList.containsByName("Nick"));
        assertTrue(contactList.containsByName("Arthur"));
        assertFalse(contactList.containsByName("Hello"));
    }
}

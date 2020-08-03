package model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Represents a list of the user's contacts.
 */

public class ContactList {
    List<Contact> contacts;

    // EFFECTS: constructs a new contact list
    public ContactList() {
        contacts = new ArrayList<>();
    }

    // EFFECTS: gets contact data for writing to JSON file
    public List<Contact> getContacts() {
        return contacts;
    }

    // EFFECTS: gets contact corresponding to the given name
    public Contact getContactByName(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds contact to list of contacts
    public boolean addContactToList(Contact contact) {
        return contacts.add(contact);
    }

    // EFFECTS: returns size of list of contacts
    public int getNumContacts() {
        return contacts.size();
    }

    // REQUIRES: 0 <= index < contacts.size()
    // EFFECTS: gets contact corresponding to the given index
    public Contact getContactFromIndex(int index) {
        return contacts.get(index);
    }

    // EFFECTS: returns true if given contact is in the list
    public Boolean containsByName(String name) {
        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

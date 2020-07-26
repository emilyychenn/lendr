package model;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    List<Contact> contactList;

    public ContactList() {
        contactList = new ArrayList<>();
    }

    public Contact getContactByName(String name) {
        for (Contact c : contactList) {
            if (c.getName() == name) {
                return c;
            }
        }
        // TODO: throw noContactFound exception!! Should prompt user to create a new contact
        return null;
    }

    public boolean addContactToList(Contact contact) {
        return contactList.add(contact);
    }

    public int getNumContacts() {
        return contactList.size();
    }

    public Contact getContactFromIndex(int index) {
        return contactList.get(index);
    }
}

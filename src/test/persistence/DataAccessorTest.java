package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;
import model.Contact;
import model.ContactList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

/*
 *  Unit tests for the DataAccessor class.
 */

public class DataAccessorTest {
    DataAccessor dataAccessor;
    ObjectMapper objectMapper;
    Account account;
    ContactList contactList;
    File file;
    private static final String FILE_PATH = "./data/usrAccountTestFile.json";

    @BeforeEach
    public void setUp() {
        dataAccessor = new DataAccessor();
        objectMapper = new ObjectMapper();
        account = new Account("PalmTree");
        contactList = new ContactList();
    }

    @Test
    public void testSaveToFile() {
        try {
            dataAccessor.saveToFile(FILE_PATH, account);
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }

        assertFalse(dataAccessor.saveToFile("./data/", account));

        assertThrows(NullPointerException.class, () -> {
            dataAccessor.saveToFile(null, account);
        });
    }

    @Test
    public void testReadFromNoFileFound() {
        assertThrows(NullPointerException.class, () -> {
            dataAccessor.readFromFile(null);
        });

        assertNull(dataAccessor.readFromFile("./data/test"));
    }

    @Test
    public void testReadFromInvalidFile() {
        Account testAccount = dataAccessor.readFromFile("./data/errorTestFile.json");

        assertThrows(NullPointerException.class, () -> {
            testAccount.getName();
        });

        assertThrows(NullPointerException.class, () -> {
            testAccount.getTransactionHistory();
        });

        assertThrows(NullPointerException.class, () -> {
            testAccount.getContactList();
        });

    }

    @Test
    public void testReadFromFile() {
        contactList.addContactToList(new Contact("Emily"));
        account.setContactList(contactList);
        dataAccessor.saveToFile(FILE_PATH, account);

        try {
            Account readAccount = dataAccessor.readFromFile(FILE_PATH);
            assertEquals(account.getName(), readAccount.getName());
            assertEquals(account.getTransactionHistory().size(), readAccount.getTransactionHistory().size());
            assertEquals(account.getContactList().countNumContacts(), readAccount.getContactList().countNumContacts());
            assertEquals(account.getContactList().getContactFromIndex(0).getName(),
                                                readAccount.getContactList().getContactFromIndex(0).getName());
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }
    }
}

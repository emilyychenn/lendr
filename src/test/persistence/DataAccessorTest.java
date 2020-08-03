package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the DataAccessor class.
 */

public class DataAccessorTest {
    DataAccessor dataAccessor;
    ObjectMapper objectMapper;
    Account account;
    File file;
    private static final String FILE_PATH = "./data/usrAccountFile.json";

    @BeforeEach
    public void setUp() {
        dataAccessor = new DataAccessor();
        objectMapper = new ObjectMapper();
        account = new Account("PalmTree");
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
    public void testReadFromFile() {
        dataAccessor.saveToFile(FILE_PATH, account);

        try {
            dataAccessor.readFromFile(FILE_PATH);
            assertEquals(account.getName(), dataAccessor.readFromFile(FILE_PATH).getName());
            assertEquals(account.getTransactionHistory().size(),
                    dataAccessor.readFromFile(FILE_PATH).getTransactionHistory().size());
            assertEquals(account.getContactList().countNumContacts(),
                    dataAccessor.readFromFile(FILE_PATH).getContactList().countNumContacts());
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }
    }
}

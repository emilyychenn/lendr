package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    public void setUp() {
        dataAccessor = new DataAccessor();
        objectMapper = new ObjectMapper();
        account = new Account("PalmTree");
        dataAccessor.saveToFile(account);
    }

    @Test
    public void testSaveToFile() {
        try {
            dataAccessor.saveToFile(account);
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }
    }

    @Test
    public void testReadFromFile() {
        try {
            dataAccessor.readFromFile();
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }
    }
}

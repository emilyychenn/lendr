package model;

/*
 *  Unit tests for the Account class.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Account testAccount;

    @BeforeEach
    public void setUp() {
        testAccount = new Account("Felix");
    }

    @Test
    public void testAccount() {
        assertEquals("Felix", testAccount.getName());
        assertEquals(0.0, testAccount.getBalance());
        assertEquals(0, testAccount.getContactList().getNumContacts());
    }
}

package model;

import exceptions.InvalidDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the DateChecker class.
 */

public class DateCheckerTest {
    DateChecker dateChecker;

    @BeforeEach
    public void setUp() {
        dateChecker = new DateChecker();
    }

    @Test
    public void testIsValidDateSuccess() {
        assertTrue(DateChecker.isValidDate("12/10/2000"));
        assertTrue(DateChecker.isValidDate("05/07/2017"));
        assertTrue(DateChecker.isValidDate("17/03/2007"));
        assertTrue(DateChecker.isValidDate("31/12/2100"));
        assertTrue(DateChecker.isValidDate("12/08/2020"));
        assertTrue(DateChecker.isValidDate("01/01/2100"));
    }

    @Test
    public void testIsValidDateOutOfBounds() {
        assertFalse(DateChecker.isValidDate("01/01/2101"));
        assertFalse(DateChecker.isValidDate("31/12/1899"));
        assertFalse(DateChecker.isValidDate("12/10/20000"));
    }

    @Test
    public void testIsValidDateDoesNotExist() {
        assertFalse(DateChecker.isValidDate("12/32/2000"));
        assertFalse(DateChecker.isValidDate("30/02/2000"));
        assertFalse(DateChecker.isValidDate("25/13/2020"));
        assertFalse(DateChecker.isValidDate("45/12/2051"));
    }

    @Test
    public void testIsValidDateIncorrectFormat() {
        assertFalse(DateChecker.isValidDate("12082020"));
        assertFalse(DateChecker.isValidDate("12-08-2020"));
        assertFalse(DateChecker.isValidDate("12/08-2020"));
        assertFalse(DateChecker.isValidDate("Aug-12-2020"));
    }

    @Test
    public void testIsValidDateNotADate() {
        assertFalse(DateChecker.isValidDate(null));
        assertFalse(DateChecker.isValidDate("dfkgdfjkng"));
        assertFalse(DateChecker.isValidDate("august"));
        assertFalse(DateChecker.isValidDate("145nj435"));
        assertFalse(DateChecker.isValidDate("df/df/dfhg"));
    }

    @Test
    public void testCheckDateSuccess() {
        try {
            DateChecker.checkDate("12/10/2000");
            assertTrue(DateChecker.isValidDate("12/10/2000"));
            DateChecker.checkDate("01/01/2100");
            assertTrue(DateChecker.isValidDate("01/01/2100"));
            DateChecker.checkDate("17/03/2007");
            assertTrue(DateChecker.isValidDate("17/03/2007"));
            DateChecker.checkDate("24/04/2020");
            assertTrue(DateChecker.isValidDate("24/04/2020"));
            DateChecker.checkDate("31/12/2100");
            assertTrue(DateChecker.isValidDate("12/08/2020"));
        } catch (InvalidDateException | ParseException e) {
            fail("Should not have thrown any exception!");
        }
    }

    @Test
    public void testCheckDateOutOfBounds() {
        try {
            DateChecker.checkDate("01/01/2101");
            assertFalse(DateChecker.checkDate("01/01/2101"));
            DateChecker.checkDate("31/12/1899");
            assertFalse(DateChecker.checkDate("31/12/1899"));
            DateChecker.checkDate("12/10/20000");
            assertFalse(DateChecker.checkDate("12/10/20000"));
            fail("Should have thrown InvalidDateException...");
        } catch (InvalidDateException e) {
            // all good!
        } catch (ParseException pe) {
            fail("Should not have thrown ParseException...");
        }
    }

    @Test
    public void testCheckDateDoesNotExist() {
        try {
            DateChecker.checkDate("12/32/2000");
            assertFalse(DateChecker.checkDate("12/32/2000"));
            DateChecker.checkDate("30/02/2000");
            assertFalse(DateChecker.checkDate("30/02/2000"));
            DateChecker.checkDate("25/13/2020");
            assertFalse(DateChecker.checkDate("25/13/2020"));
            DateChecker.checkDate("45/12/2051");
            assertFalse(DateChecker.checkDate("45/12/2051"));
            fail("Should have thrown ParseException...");
        } catch (ParseException e) {
            // all good!
        } catch (InvalidDateException e) {
            fail("Should not have thrown InvalidDateException...");
        }
    }

    @Test
    public void testCheckDateIncorrectFormat() {
        try {
            DateChecker.checkDate("12082020");
            assertFalse(DateChecker.checkDate("12082020"));
            DateChecker.checkDate("12-08-2020");
            assertFalse(DateChecker.checkDate("12-08-2020"));
            DateChecker.checkDate("12/08-2020");
            assertFalse(DateChecker.checkDate("12/08-2020"));
            DateChecker.checkDate("Aug-12-2020");
            assertFalse(DateChecker.checkDate("Aug-12-2020"));
            fail("Should have thrown ParseException...");
        } catch (ParseException e) {
            // all good!
        } catch (InvalidDateException e) {
            fail("Should not have thrown InvalidDateException...");
        }
    }

    @Test
    public void testCheckDateNotADate() {
        try {
            DateChecker.checkDate(null);
            assertFalse(DateChecker.checkDate(null));
            DateChecker.checkDate("dfkgdfjkng");
            assertFalse(DateChecker.checkDate("dfkgdfjkng"));
            DateChecker.checkDate("august");
            assertFalse(DateChecker.checkDate("august"));
            DateChecker.checkDate("145nj435");
            assertFalse(DateChecker.checkDate("145nj435"));
            DateChecker.checkDate("df/df/dfhg");
            assertFalse(DateChecker.checkDate("df/df/dfhg"));
            fail("Should have thrown ParseException...");
        } catch (ParseException e) {
            // all good!
        } catch (InvalidDateException e) {
            fail("Should not have thrown InvalidDateException...");
        }
    }
}

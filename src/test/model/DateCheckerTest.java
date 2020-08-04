package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testIsValidDate() {
        assertTrue(DateChecker.isValidDate("12/10/2000"));
        assertFalse(DateChecker.isValidDate("12/32/2000"));
        assertFalse(DateChecker.isValidDate("01/01/2101"));
        assertFalse(DateChecker.isValidDate("31/12/1899"));
        assertFalse(DateChecker.isValidDate("12/10/20000"));
        assertFalse(DateChecker.isValidDate("30/02/2000"));
        assertFalse(DateChecker.isValidDate(null));
    }
}

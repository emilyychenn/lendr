package model;
import model.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Unit tests for the Loan class. Note: will add more tests later on when Loan class is used more extensively
 *  (e.g. to show full loan history with dates etc.)
 */

public class LoanTest {
    Loan testLoan;

    @BeforeEach
    public void setUp() {
        testLoan = new Loan(-100.0, "12/12/2019");
    }

    @Test
    public void testLoan() {
        assertEquals(-100.0, testLoan.getTotalAmountOwed());
        assertEquals("12/12/2019", testLoan.getDateOwed());
    }

}

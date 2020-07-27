package exceptions;

/*
 *  Unit tests for the InvalidDateException.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.LoanApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidDateExceptionTest {
    LoanApp loanAppTest;
    String dateToValidate;
    String dateFormat;
    SimpleDateFormat sdf;
//    sdf.setLenient(false);

    @BeforeEach
    public void setUp() {
        loanAppTest = new LoanApp();
        dateToValidate = "12/12/2020";
        dateFormat =  "dd/MM/yyyy";
        sdf = new SimpleDateFormat(dateFormat);
    }

    @Test
    public void TestInvalidDateException() throws Exception {
        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
            if (!loanAppTest.checkValidYear(dateToValidate)) {
                throw new InvalidDateException();
            }
            fail("Expected exception not thrown");
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }
}

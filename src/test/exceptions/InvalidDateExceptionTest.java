package exceptions;

/*
 *  Unit tests for the InvalidDateException.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


public class InvalidDateExceptionTest {
    String dateToValidate;
    String dateFormat;
    String errorMsg;
    SimpleDateFormat sdf;
    InvalidDateException invalidDateException;

    @BeforeEach
    public void setUp() {
        dateToValidate = "12/12/2020";
        dateFormat =  "dd/MM/yyyy";
        sdf = new SimpleDateFormat(dateFormat);
        errorMsg = " gives an error message!!";
    }

    @Test
    public void testInvalidDateException() {
        invalidDateException = new InvalidDateException(dateToValidate, errorMsg);
        assertEquals(dateToValidate + errorMsg, invalidDateException.getMessage());
    }
}

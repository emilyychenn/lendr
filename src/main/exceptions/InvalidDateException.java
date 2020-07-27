package exceptions;

/**
 *  Signals that the given date is invalid (past the latest possible date of Dec 31, 2100).
 */

public class InvalidDateException extends Exception {

    public InvalidDateException(String date, String errMsg) {
        super(date + errMsg);
    }

}

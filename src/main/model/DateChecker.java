package model;

import exceptions.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateChecker {
    private static final String ERROR_MSG = " is an invalid date. Date must be in format DD/MM/YYYY with a date "
            + "between Jan 1, 1900 and Dec 31, 2100.";

    // EFFECTS: Constructs new date checker
    public DateChecker() {
    }

    // REQUIRES: valid date format 'DD/MM/YYYY' and valid date
    // EFFECTS: checks that the date is in the correct format and is indeed a real date
    public static boolean isValidDate(String dateToValidate) {
        String dateFormat = "dd/MM/yyyy";

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
            Calendar latestDate = new GregorianCalendar();
            latestDate.set(2100, Calendar.DECEMBER, 31);

            Calendar earliestDate = new GregorianCalendar();
            earliestDate.set(1900, Calendar.JANUARY, 1);

            if (date.after(latestDate.getTime()) || date.before(earliestDate.getTime())) {
                throw new InvalidDateException(dateToValidate, ERROR_MSG);
            }
            System.out.println(dateToValidate + " is a valid date.");
        } catch (DateTimeParseException | ParseException | InvalidDateException e) {
            System.out.println(dateToValidate + ERROR_MSG);
            return false;
        }
        return true;
    }
}

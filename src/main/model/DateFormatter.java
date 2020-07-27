package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    Date expires;

    public DateFormatter() {

    }

    public static boolean checkValidDate(String dateToValidate) {
        String dateFormat = "dd/MM/yyyy";
        // TODO: somehow the data 12/12/201030 is reading as a valid date... double check dateFormat rules

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate); // will throw ParseException if invalid
            System.out.println(dateToValidate + " is a valid date.");
        } catch (ParseException e) {
            System.out.println(dateToValidate + " is an invalid date. Please re-select contact and re-enter date"
                    + " in format DD/MM/YYYY.");
            return false;
        }
        return true;
    }

    public boolean hasExpiration() {
        return expires == null;
    }

    public Date getExpirationDate() {
        return expires;
    }

    public boolean hasExpired(Date date) {
        if (expires == null) {
            return true;
        } else {
            return date.before(expires);
        }
    }

}



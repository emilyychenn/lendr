package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Test {

    public static boolean validateJavaDate(String strDate) {
        if (strDate.trim().equals("")) {
            return true;
        } else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/DD/YYYY");
            sdfrmt.setLenient(false);
            /* Create Date object parse the string into date */
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is valid date format");
            } catch (ParseException e) {
                System.out.println(strDate + " is an invalid date. Please enter date in format MM/DD/YYYY");
                return false;
            }
            return true; /* Return true if date format is valid */
        }
    }

    public static void main(String[] args) {
        validateJavaDate("12/29/2016");
        validateJavaDate("12-29-2016");
        validateJavaDate("12,29,2016");
        validateJavaDate("12/32/3123");
    }
}

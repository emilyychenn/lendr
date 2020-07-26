package date;

import java.util.regex.Pattern;

public class FormattedDateMatcher implements DateMatcher {

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");

    @Override
    public boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

}

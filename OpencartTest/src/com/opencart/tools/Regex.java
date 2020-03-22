package com.opencart.tools;

import java.util.regex.*;

public final class Regex {

    private final static String PATTERN_NUMBER = "\\d+";
    private final static String CATCH_NUMBER_MESSAGE = "NumberFormatException for pattern =  %s text =  %s";

    private Regex() {
    }

    public static String takeString(String pattern, String text) {
        String line = new String();
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            line = text.substring(matcher.start(), matcher.end());
        }
        return line;
    }

    public static int takeNumber(String text) {
        int number = -1;
        String takenText = takeString(PATTERN_NUMBER, text);
        if (!takenText.isEmpty()) {
            try {
                number = Integer.parseUnsignedInt(takenText);
            } catch (NumberFormatException e) {
                throw new RuntimeException(String.format(CATCH_NUMBER_MESSAGE, PATTERN_NUMBER, text));
            }
        }
        return number;
    }
}

package com.airtribe.meditrack.util;

import java.util.regex.Pattern;

public class Validator {

    private static final String PHONE_REGEX = "\\d{10}";

    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        // Match against the regex
        return Pattern.matches(PHONE_REGEX, phone);
    }
}

package com.management.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private final String REGEX_PATTERN_FOR_EMAIL = "^(.+)@(\\S+)$";

    public boolean emailValidation(String email){
        Pattern pattern = Pattern.compile(REGEX_PATTERN_FOR_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

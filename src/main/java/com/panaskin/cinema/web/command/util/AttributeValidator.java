package com.panaskin.cinema.web.command.util;

import java.util.regex.Pattern;

public class AttributeValidator {
    public static boolean idValidate(String id) {
        return (notNull(id) || isDigit(id));
    }
    
    public static boolean userValidate(String user) {
        return notNull(user);
    }
    
    private static boolean notNull(String id) {
        if (id != null) {
            return true;
        }
        return false;
    }

    private static boolean isDigit(String id) {
        if (Pattern.matches("\\-?\\d+", (CharSequence) id)) {
            return true;
        }
        return false;
    }
}

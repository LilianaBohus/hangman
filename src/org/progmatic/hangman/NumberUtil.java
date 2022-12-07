package org.progmatic.hangman;

import java.util.regex.Pattern;

public class NumberUtil {

    private static final Pattern pattern = Pattern.compile("[0-9]+");

    public static boolean isNaN(String test) {
        if (test == null) {
            return false;
        }
        return !pattern.matcher(test).matches();
    }

    public static boolean isBiggerThan(int test, int pattern) {
        return test > pattern;
    }

    public static boolean isLessThan(int test, int pattern) {
        return test < pattern;
    }
}

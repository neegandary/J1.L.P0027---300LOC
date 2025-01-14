package Utilities;

import java.util.List;

public class DataValidatation {
    public static boolean checkStringEmpty(String value) {
        return value.isEmpty();
    }

    public static boolean checkStringWithFormat(String value, String pattern) {
        return value.matches(pattern);
    }

    public static boolean checkMatchCode(String value, List<String> pattern) {
        return pattern.contains(value);
    }

}
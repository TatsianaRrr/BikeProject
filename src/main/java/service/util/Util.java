package service.util;

import service.util.exception.UtilException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final String LOGIN_REGEXP = "^[0-9a-zA-Z_-]{3,15}$";
    private static final String PASSWORD_REGEXP = "^[\\w\\-]{8,20}$";
    private static final String EMAIL_REGEXP = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}";
    private static final String EN_NAMES_REGEXP = "^[a-zA-Z]+$";
    private static final String RU_NAMES_REGEXP = "^[а-яА-Я]+$";
    private static final String DATE = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12]\\d|3[01])$";
    private static final String CORRECT_STRING = "[A-Za-zА-Яа-яёЁ]+";

    public static void isNull(Object... objects) throws UtilException {
        for (Object ob : objects) {
            if (ob == null) {
                throw new UtilException("data entry error (null string)");
            }
        }
    }

    public static void isEmptyString(String... strings) throws UtilException {
        for (String s : strings) {
            if (s.isEmpty()) {
                throw new UtilException("data entry error (empty string)");
            }
        }
    }

    public static void isEmptyArray(String[]... masStrings) throws UtilException {
        for (String[] mas : masStrings) {
            for (String s : mas) {
                if (s.isEmpty()) {
                    throw new UtilException("data entry error (empty string)");
                }
            }
        }
    }

    public static void matchDate(String... strings) throws UtilException {
        Pattern pattern = Pattern.compile(DATE);
        try {
            for (String s : strings) {
                Matcher matcher = pattern.matcher(s);
                if (!matcher.matches()) {
                    throw new UtilException("date input error");
                }
            }
        } catch (NullPointerException e) {
            throw new UtilException(e);
        }
    }

    public static void matchCorrectString(String... strings) throws UtilException {
        Pattern pattern = Pattern.compile(CORRECT_STRING);
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new UtilException("invalid String in name field");
            }
        }
    }

    public static void matchEmail(String... strings) throws UtilException {
        Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new UtilException("email must have format: anna@gmail.com");
            }
        }
    }

    public static void correctLogin(String... strings) throws UtilException {
        Pattern pattern = Pattern.compile(LOGIN_REGEXP);
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new UtilException("invalid login: it must contain at least 3 letters or numbers");
            }
        }
    }

    public static void correctPassword(String... strings) throws UtilException {
        Pattern pattern = Pattern.compile(PASSWORD_REGEXP);
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new UtilException("invalid password: it must contain at least 8 letters or numbers");
            }
        }
    }
}

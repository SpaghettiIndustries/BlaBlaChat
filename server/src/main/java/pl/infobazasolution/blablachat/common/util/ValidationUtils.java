package pl.infobazasolution.blablachat.common.util;

import org.apache.commons.validator.EmailValidator;

public class ValidationUtils {

    public static Boolean containsWhitespace(String str) {
        if (!(str != null && str.length() > 0)) {
            return false;
        }

        int strLen = str.length();

        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }
}

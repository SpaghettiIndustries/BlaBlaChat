package pl.infobazasolution.blablachat.common.exception.utils;

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
}

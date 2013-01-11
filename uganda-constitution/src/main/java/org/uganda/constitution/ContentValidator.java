package org.uganda.constitution;

/**
 * @author Jonathan
 */
public class ContentValidator {

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

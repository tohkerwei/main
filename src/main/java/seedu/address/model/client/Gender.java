package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's gender in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should be Male/Female/Others";

    public final String value;

    /**
     * Constructs an {@code Gender}.
     *
     * @param gender Male / Female / Others.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidGender(String test) {
        requireNonNull(test);
        String testInLowerCase = test.trim().toLowerCase();
        return test.equals("")
            || testInLowerCase.equals("male")
            || testInLowerCase.equals("m")
            || testInLowerCase.equals("female")
            || testInLowerCase.equals("f")
            || testInLowerCase.equals("others")
            || testInLowerCase.equals("o");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                        && firstCharEquals(value, ((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if the first character of two strings are the same.
     */
    private static boolean firstCharEquals(String string1, String string2) {
        if (string1.length() == 0 || string2.length() == 0) {
            return false;
        } else {
            return string1.toLowerCase().charAt(0) == string2.toLowerCase().charAt(0);
        }
    }

}

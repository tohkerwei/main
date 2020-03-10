package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's gender in the address book.
 * Is valid as declared in {@link #isValidGender(String)}
 */
public enum Gender {
    MALE, FEMALE, OTHERS;

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should be Male/Female/Others, and it should not be blank";

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidGender(String test) {
        requireNonNull(test);
        String testInLowerCase = test.toLowerCase();
        return testInLowerCase.equals("male") || testInLowerCase.equals("female") || testInLowerCase.equals("others");
    }
}

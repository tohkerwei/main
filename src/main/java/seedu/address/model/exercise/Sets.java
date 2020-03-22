package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of Sets a client does for an exercise. Guarantees:
 * immutable; is valid as declared in {@link #isValidSets(String)}
 */
public class Sets {

    public static final String MESSAGE_CONSTRAINTS = "Input Sets must either be whole number (eg. 65)";
    public static final String VALIDATION_REGEX = "[0-9]?";
    public final String value;

    /**
     * Constructs a {@code Sets}.
     *
     * @param sets A whole number.
     */
    public Sets(String sets) {
        requireNonNull(sets);
        checkArgument(isValidSets(sets), MESSAGE_CONSTRAINTS);
        value = sets;
    }

    /**
     * Returns true if a given string is a valid sets.
     */
    public static boolean isValidSets(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sets // instanceof handles nulls
                        && value.equals(((Sets) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

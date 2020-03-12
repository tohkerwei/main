package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's sports in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSports(String)}
 */
public class Sports {

    public static final String MESSAGE_CONSTRAINTS = "Sports can take any value, it should not be blank";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String value;

    /**
     * Constructs an {@code Sports}.
     *
     * @param sports A valid Sports.
     */
    public Sports(String sports) {
        requireNonNull(sports);
        checkArgument(isValidSports(sports), MESSAGE_CONSTRAINTS);
        value = sports;
    }

    /**
     * Returns true if a given string is a valid sports.
     */
    public static boolean isValidSports(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sports // instanceof handles nulls
                && value.equals(((Sports) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

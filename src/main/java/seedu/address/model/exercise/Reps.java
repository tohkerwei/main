package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of Reps a client does for an exercise. Guarantees:
 * immutable; is valid as declared in {@link #isValidReps(String)}
 */
public class Reps {

    public static final String MESSAGE_CONSTRAINTS = "Input Reps must be whole number (eg. 65)";
    public static final String VALIDATION_REGEX = "[0-9]?";
    public final String value;

    /**
     * Constructs a {@code Reps}.
     *
     * @param sets A whole number.
     */
    public Reps(String reps) {
        requireNonNull(reps);
        checkArgument(isValidReps(reps), MESSAGE_CONSTRAINTS);
        value = reps;
    }

    /**
     * Returns true if a given string is a valid reps.
     */
    public static boolean isValidReps(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reps // instanceof handles nulls
                        && value.equals(((Reps) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

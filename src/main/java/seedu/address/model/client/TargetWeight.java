package seedu.address.model.client;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents current weight (in kg) of a client in FitBiz. Guarantees:
 * immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class TargetWeight {

    public static final String MESSAGE_CONSTRAINTS =
            "Input weight must either be whole or decimal number (eg. 65 or 86.22)";
    public static final String VALIDATION_REGEX = "[0-9]+(\\.[0-9]+)?";
    public final String value;

    /**
     * Constructs a {@code TargetWeight}.
     *
     * @param phone A valid weight.
     */
    public TargetWeight(String weight) {
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Returns true if a given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TargetWeight // instanceof handles nulls
                        && value.equals(((TargetWeight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

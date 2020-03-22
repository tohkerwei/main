package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the weight (in kg) of an exercise done by a client in FitBiz. Guarantees:
 * immutable; is valid as declared in {@link #isValidExerciseWeight(String)}
 */
public class ExerciseWeight {

    public static final String MESSAGE_CONSTRAINTS = "Input weight must either be whole number (eg. 65)";
    public static final String VALIDATION_REGEX = "[0-9]?";
    public final String value;

    /**
     * Constructs a {@code ExerciseWeight}.
     *
     * @param exerciseWeight A valid exerise weight.
     */
    public ExerciseWeight(String exerciseWeight) {
        requireNonNull(exerciseWeight);
        checkArgument(isValidExerciseWeight(exerciseWeight), MESSAGE_CONSTRAINTS);
        value = exerciseWeight;
    }

    /**
     * Returns true if a given string is a valid exerise weight.
     */
    public static boolean isValidExerciseWeight(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseWeight // instanceof handles nulls
                        && value.equals(((ExerciseWeight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the data of an exercise done by client. Guarantees: immutable; is
 * valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
        "Date input should be in the format DD-MM-YYYY and it should not be blank";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate value;
    public final String displayValue;

    /**
     * Constructs a {@code Date}.
     *
     * @param birthday A valid date in the form DD-MM-YYYY.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(date, DATE_TIME_FORMATTER);
        this.displayValue = date; // assuming birthday string is valid
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

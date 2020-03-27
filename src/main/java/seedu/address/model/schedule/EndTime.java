package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the end time of a schedule of a client. Guarantees: immutable; is
 * valid as declared in {@link #isValidTimingFormat(String)}
 */
public class EndTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Date input should be in the format DD-MM-YYYY and it should not be blank";
    public final String value;
    public final int directTimeInt;

    /**
     * Constructs a {@code Date}.
     *
     * @param endTime A valid end time in the form HHMM
     */
    public EndTime(String endTime) {
        requireNonNull(endTime);
        checkArgument(isValidTimingFormat(endTime), MESSAGE_CONSTRAINTS);
        this.value = formatTime(endTime);
        this.directTimeInt = Integer.parseInt(endTime);
    }

    /**
     * Checks if the input timing is of valid "HHMM" format.
     ** @param time
     * @return true if time is of correct format of "HHMM"
     */
    public static boolean isValidTimingFormat(String time) {
        try {
            String formattedTime = time.substring(0, 2) + ":" + time.substring(2, 4);
            LocalTime.parse(formattedTime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Formats a HHMM time to HH:MM
     * @param time
     * @return formatted time in HH:MM
     */
    public String formatTime(String time) {
        String formattedEndTime = time.substring(0, 2) + ":" + time.substring(2, 4);
        return formattedEndTime;
    }

    public String getTime() {
        String time = value.substring(0, 2) + value.substring(3, 5);
        return time;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && value.equals(((EndTime) other).value)); // state check
    }

    public int getDirectTimeInt() {
        return this.directTimeInt;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}


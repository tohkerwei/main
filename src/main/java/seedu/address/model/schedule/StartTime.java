package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the start time of a schedule of a client. Guarantees: immutable; is
 * valid as declared in {@link #isValidTimingFormat(String)}
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Date input should be in the format DD-MM-YYYY and it should not be blank";
    public final String value;
    public final int directTimeInt;

    /**
     * Constructs a {@code Date}.
     *
     * @param startTime A valid start time in the form HHMM
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidTimingFormat(startTime), MESSAGE_CONSTRAINTS);
        this.value = formatTime(startTime);
        this.directTimeInt = Integer.parseInt(startTime);
    }

    /**
     * Checks if the input timing is of valid "HHMM" format.
     * * @param time
     *
     * @return true if time is of correct format of "HHMM"
     */
    public static boolean isValidTimingFormat(String time) {
        try {
            if (time.length() != 4) {
                return false;
            }
            String formattedTime = time.substring(0, 2) + ":" + time.substring(2, 4);
            LocalTime.parse(formattedTime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public StartTime clone() {
        String rawStartTime = this.value.substring(0, 2) + this.value.substring(3);
        return new StartTime(rawStartTime);
    }

    /**
     * Formats a time into HH:MM format
     *
     * @param time
     * @return formatted time in HH:MM format
     */
    public String formatTime(String time) {
        String formattedStartTime = time.substring(0, 2) + ":" + time.substring(2, 4);
        return formattedStartTime;
    }

    public String getTime() {
        String time = value.substring(0, 2) + value.substring(3, 5);
        return time;
    }

    public int getDirectTimeInt() {
        return this.directTimeInt;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}


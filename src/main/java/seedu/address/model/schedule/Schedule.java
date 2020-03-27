package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


/**
 * Represents a Schedule in the Client.
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS = "Schedule day should be the first 3 letters of the day. "
            + "Timings should be given in 24 hour format and in 30 minute denominations. "
            + "Start time cannot be later than end time.";

    private final Day day;
    private final StartTime startTime;
    private final EndTime endTime;

    public Schedule(Day day, StartTime startTime, EndTime endTime) {
        requireAllNonNull(day, startTime, endTime);
        checkArgument(isValidTimeFrame(startTime.toString(), endTime.toString()), MESSAGE_CONSTRAINTS);
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** @author Dban1
     * Checks if end time is later than start time, assuming they are of valid format "hhmm". End time
     * and start time cannot be simultaneously 0000. If end time is 0000, it is assumed as the next day.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isValidTimeFrame(String startTime, String endTime) {
        // End time cannot be 0000
        if (endTime.equals("00:00")) {
            return false;
        }
        // Start time cannot equal End time
        if (startTime.equals(endTime)) { return false; }
        // Check if End time is later than Start time
        return (LocalTime.parse(endTime).compareTo(LocalTime.parse(startTime)) > 0);
    }

    public Day getDay() {
        return this.day;
    }

    public StartTime getStartTime() {
        return this.startTime;
    }

    public EndTime getEndTime() {
        return this.endTime;
    }

    public double getHours() {
        LocalTime start = LocalTime.parse(this.startTime.getTime());
        LocalTime end = LocalTime.parse(this.endTime.getTime());
        return (double) ChronoUnit.HOURS.between(start, end);
    }

    /**
     * Returns true if both schedule have the same attribute values
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.getDay().equals(getDay())
                && otherSchedule.getStartTime().equals(getStartTime())
                && otherSchedule.getEndTime().equals(getEndTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(day, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDay())
                .append(" Time: ")
                .append(getStartTime())
                .append(" - ")
                .append(getEndTime());
        return builder.toString();
    }

}

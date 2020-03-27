package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


/**
 * Represents a Schedule in the Client.
 */
public class Schedule implements Comparable<Schedule>{
    public static final String MESSAGE_CONSTRAINTS = "Schedule day should be the first 3 letters of the day. "
            + "Timings should be given in 24 hour format and in HHmm format. "
            + "Start time cannot be later than end time. Maximum range is 0000-2359";

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

    /**
     * @param startTime
     * @param endTime
     * @return
     * @author Dban1
     * Checks if end time is later than start time, assuming they are of valid format "hhmm". End time
     * and start time cannot be simultaneously 0000. If end time is 0000, it is assumed as the next day.
     */
    public static boolean isValidTimeFrame(String startTime, String endTime) {
        // End time cannot be 0000
        if (endTime.equals("00:00")) {
            return false;
        }
        // Start time cannot equal End time
        if (startTime.equals(endTime)) {
            return false;
        }
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
        int otherSt = Integer.parseInt(otherSchedule.getStartTime().toString());
        int otherEt = Integer.parseInt(otherSchedule.getEndTime().toString());
        int thisSt  = Integer.parseInt(this.getStartTime().toString());
        int thisEt  = Integer.parseInt(this.getEndTime().toString());

        // Checks if there are overlaps between 2 schedules.
        return otherSchedule.getDay().equals(getDay())
                && (thisSt == otherSt)
                && (thisEt == otherEt);
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

    @Override
    public int compareTo(Schedule other) {
        Schedule otherSchedule = other;
        int otherSt = other.getStartTime().getDirectTimeInt();
        int otherEt = other.getEndTime().getDirectTimeInt();
        int thisSt  = this.getStartTime().getDirectTimeInt();
        int thisEt  = this.getEndTime().getDirectTimeInt();
        // Checks if there are overlaps between 2 schedules.
        if (otherSchedule.getDay().equals(getDay())) {
            if( ((otherSt < thisSt) && (otherEt < thisSt)) || (otherSt > thisEt) ) {
                return otherSt - thisSt;
            } else {
                return 0;
            }
        }
        return otherSt - thisSt;
    }
}

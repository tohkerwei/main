package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import seedu.address.model.schedule.DayEnum.WeekDay;

/**
 * Represents a Schedule in the Client.
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS = "Schedule timings should be given in 24 hour timing, in"
            + " 30 minute denominations";

    private String startTime;
    private String endTime;
    private WeekDay day;

    public Schedule(String day, String startTime, String endTime) {
        requireAllNonNull(day, startTime, endTime);
        isValidDay(day);
        isValidTimingFormat(startTime, endTime);
        assignDay(day);
        assignTimes(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Checks if input String is a valid three-lettered day.
     *
     * @param test
     * @return
     */
    public static boolean isValidDay(String test) {
        String lowerCaseTest = test.toLowerCase();
        switch (lowerCaseTest) {
        case "sun":
        case "mon":
        case "tue":
        case "wed":
        case "thu":
        case "fri":
        case "sat":
            return true;
        default:
            return false;
        }
    }

    /**
     * Checks if the input timings are of valid "hhmm" format.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isValidTimingFormat(String startTime, String endTime) {
        try {
            String formattedStartTime = startTime.substring(0, 2) + ":" + startTime.substring(2, 4);
            String formattedEndTime = endTime.substring(0, 2) + ":" + endTime.substring(2, 4);
            LocalTime.parse(formattedStartTime);
            LocalTime.parse(formattedEndTime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if end time is later than start time, assuming they are of valid format "hhmm". End time
     * and start time cannot be simultaneously 0000. If end time is 0000, it is assumed as the next day.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isValidTimeFrame(String startTime, String endTime) {
        if (startTime.equals("0000") && endTime.equals("0000")) {
            return false;
        }
        return (Integer.parseInt(endTime) - Integer.parseInt(startTime) > 0);
    }

    /**
     * Assigns day to this Schedule object.
     *
     * @param day
     */
    private void assignDay(String day) {
        switch (day) {
        case "sun":
            this.day = WeekDay.SUN;
            return;
        case "mon":
            this.day = WeekDay.MON;
            return;
        case "tue":
            this.day = WeekDay.TUE;
            return;
        case "wed":
            this.day = WeekDay.WED;
            return;
        case "thu":
            this.day = WeekDay.THU;
            return;
        case "fri":
            this.day = WeekDay.FRI;
            return;
        case "sat":
            this.day = WeekDay.SAT;
            return;
        default:
            return;
        }
    }

    /**
     * Assigns start time and end time to this Schedule object.
     *
     * @param startTime
     * @param endTime
     */
    private void assignTimes(String startTime, String endTime) {
        String formattedStartTime = startTime.substring(0, 2) + ":" + startTime.substring(2, 4);
        String formattedEndTime = endTime.substring(0, 2) + ":" + endTime.substring(2, 4);
        this.startTime = formattedStartTime;
        this.endTime = formattedEndTime;
    }

    public WeekDay getDay() {
        return this.day;
    }

    public double getHours() {
        LocalTime start = LocalTime.parse(this.startTime);
        LocalTime end = LocalTime.parse(this.endTime);
        return (double) ChronoUnit.HOURS.between(start, end);
    }

}

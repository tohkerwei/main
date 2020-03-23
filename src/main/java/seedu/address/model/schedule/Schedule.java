package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.schedule.DayEnum.WEEKDAY;

public class Schedule {

    public static final String MESSAGE_CONSTRAINTS = "Schedule timings should be given in 24 hour timing, in"
            + " 30 minute denominations";

    public final String day;
    public final String startTime;
    public final String endTime;

    public Schedule(String day, String startTime, String endTime) {
        requireAllNonNull(day, startTime, endTime);
        isValidDay(day);
        isValidTimingFormat(startTime, endTime);
        assignDay(day);
        assignTimes(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static boolean isValidDay(String test) {
        String lowerCaseTest = test.toLowerCase();
        switch(lowerCaseTest) {
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

    public static boolean isValidTimingFormat(String startTime, String endTime) {
        try {
            String formattedStartTime = startTime.substring(0, 2) + ":" + startTime.substring(2, 4);
            String formattedEndTime = endTime.substring(0, 2) + ":" + endTime.substring(2, 4);
            LocalTime.parse(formattedStartTime);
            LocalTime.parse(formattedEndTime);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isValidTimeFrame()
}

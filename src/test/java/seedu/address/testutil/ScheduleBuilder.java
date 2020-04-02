package seedu.address.testutil;

import seedu.address.model.schedule.Day;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {

    public static final String DEFAULT_DAY = ("MON");
    public static final String DEFAULT_START_TIME = "1100";
    public static final String DEFAULT_END_TIME = "1200";

    private Day day;
    private StartTime startTime;
    private EndTime endTime;

    public ScheduleBuilder() {
        day = new Day(DEFAULT_DAY);
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
    }

    public Schedule build() {
        return new Schedule(day, startTime, endTime);
    }

}

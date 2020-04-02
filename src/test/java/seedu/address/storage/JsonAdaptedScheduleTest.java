package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.ScheduleBuilder;

class JsonAdaptedScheduleTest {

    private static final String INVALID_DAY = "RANDOMDAY";
    private static final String INVALID_START_TIME = "55:55";
    private static final String INVALID_END_TIME = "99:99";

    private static final String VALID_DAY = "MON";
    private static final String VALID_START_TIME = "11:00";
    private static final String VALID_END_TIME = "12:00";

    @Test
    public void toModelType_validSchedule_returnsSchedule() {
        Schedule testSchedule = new ScheduleBuilder().withDay(VALID_DAY).withStartTime(VALID_START_TIME)
                .withEndTime(VALID_END_TIME).build();
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(testSchedule);

        assertEquals(testSchedule, schedule);
    }
}

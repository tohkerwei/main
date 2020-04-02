package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;

class DayEnumTest {
    @Test
    public void valueOfTest() {
        assertTrue(DayEnum.Weekday.valueOf("MON") == DayEnum.Weekday.MON);
    }
}

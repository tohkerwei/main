package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SportsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sports(null));
    }

    @Test
    public void constructor_invalidSports_throwsIllegalArgumentException() {
        String invalidSports = "";
        assertThrows(IllegalArgumentException.class, () -> new Sports(invalidSports));
    }

    @Test
    public void isValidSports() {
        // null Sports
        assertThrows(NullPointerException.class, () -> Sports.isValidSports(null));

        // invalid sports
        assertFalse(Sports.isValidSports("")); // empty string
        assertFalse(Sports.isValidSports(" ")); // spaces only

        // valid sports
        assertTrue(Sports.isValidSports("-")); // no sports
        assertTrue(Sports.isValidSports("hockey")); // one sport
        assertTrue(Sports.isValidSports("track, frisbee, water polo")); // multiple sports
        assertTrue(Sports.isValidSports("Cross-country mountain biking, 10km run")); // sports with "-" and numbers
    }
}

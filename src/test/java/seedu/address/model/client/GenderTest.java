package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid genders
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("tranny"));
        assertFalse(Gender.isValidGender("shemale"));
        assertFalse(Gender.isValidGender("Other")); // match with Others
        assertFalse(Gender.isValidGender("23552")); // numeric
        assertFalse(Gender.isValidGender("MaleFemale")); // multiple
        assertFalse(Gender.isValidGender("Ma le")); // spaces within
        assertFalse(Gender.isValidGender("feMa3le"));

        // different gender enum
        assertFalse(Gender.MALE == Gender.FEMALE);
        assertFalse(Gender.FEMALE == Gender.OTHERS);
        assertFalse(Gender.MALE == Gender.OTHERS);

        // valid gender
        assertTrue(Gender.isValidGender("Male"));
        assertTrue(Gender.isValidGender("Female"));
        assertTrue(Gender.isValidGender("Others"));
        assertTrue(Gender.isValidGender("male"));
        assertTrue(Gender.isValidGender("feMale"));
        assertTrue(Gender.isValidGender("others"));

    }
}

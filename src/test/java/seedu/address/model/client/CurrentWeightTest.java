package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentWeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentWeight(null));
    }

    @Test
    public void constructor_invalidCurrentWeight_throwsIllegalArgumentException() {
        String invalidCurrentWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new CurrentWeight(invalidCurrentWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight
        assertThrows(NullPointerException.class, () -> CurrentWeight.isValidWeight(null));

        // invalid weight
        assertFalse(CurrentWeight.isValidWeight("")); // empty string
        assertFalse(CurrentWeight.isValidWeight(" ")); // spaces only
        assertFalse(CurrentWeight.isValidWeight("peter")); // contains no numbers
        assertFalse(CurrentWeight.isValidWeight(".2")); // no leading number before decimal
        assertFalse(CurrentWeight.isValidWeight("2.2.2")); // too many decimal points
        assertFalse(CurrentWeight.isValidWeight("-23")); // negative numbers
        assertFalse(CurrentWeight.isValidWeight("-23.23")); // negative decimals

        // valid weight
        assertTrue(CurrentWeight.isValidWeight("45")); // numbers only
        assertTrue(CurrentWeight.isValidWeight("45.23")); // numbers with decimal points
        assertTrue(CurrentWeight.isValidWeight("0.1")); // numbers with decimal points and leading 0
        assertTrue(CurrentWeight.isValidWeight("412343212384755.34573223138567385432")); // arbitrarily long numbers
    }
}

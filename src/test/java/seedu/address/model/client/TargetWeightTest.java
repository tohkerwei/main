package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TargetWeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TargetWeight(null));
    }

    @Test
    public void constructor_invalidTargetWeight_throwsIllegalArgumentException() {
        String invalidTargetWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new TargetWeight(invalidTargetWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight
        assertThrows(NullPointerException.class, () -> TargetWeight.isValidWeight(null));

        // invalid weight
        assertFalse(TargetWeight.isValidWeight("")); // empty string
        assertFalse(TargetWeight.isValidWeight(" ")); // spaces only
        assertFalse(TargetWeight.isValidWeight("ab")); // contains no numbers
        assertFalse(TargetWeight.isValidWeight("a.b")); // contains no numbers
        assertFalse(TargetWeight.isValidWeight(".2")); // no leading number before decimal
        assertFalse(TargetWeight.isValidWeight("2.2.2")); // too many decimal points
        assertFalse(TargetWeight.isValidWeight("-23")); // negative numbers
        assertFalse(TargetWeight.isValidWeight("-23.23")); // negative decimals
        assertFalse(TargetWeight.isValidWeight("as.23")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23.as")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23.a1")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23z1")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23,23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23|23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23/23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23./23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23/.23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("2-3.23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23.2'3")); // wrong symbols

        // valid weight
        assertTrue(TargetWeight.isValidWeight("0")); // single digit
        assertTrue(TargetWeight.isValidWeight("5")); // single digit
        assertTrue(TargetWeight.isValidWeight("456")); // numbers only
        assertTrue(TargetWeight.isValidWeight("1.2")); // numbers with decimal points
        assertTrue(TargetWeight.isValidWeight("45.23")); // numbers with decimal points
        assertTrue(TargetWeight.isValidWeight("0.1")); // numbers with decimal points and leading 0
        assertTrue(TargetWeight.isValidWeight("412343212384755.34573223138567385432")); // arbitrarily long numbers
    }
}

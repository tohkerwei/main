package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidHeight_throwsIllegalArgumentException() {
        String invalidHeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidHeight));
    }

    @Test
    public void isValidHeight() {
        // null height
        assertThrows(NullPointerException.class, () -> Height.isValidHeight(null));

        // invalid height
        assertFalse(Height.isValidHeight("")); // empty string
        assertFalse(Height.isValidHeight(" ")); // spaces only
        assertFalse(Height.isValidHeight("ab")); // contains no numbers
        assertFalse(Height.isValidHeight("a.b")); // contains no numbers
        assertFalse(Height.isValidHeight(".2")); // no leading number before decimal
        assertFalse(Height.isValidHeight("2.2.2")); // too many decimal points
        assertFalse(Height.isValidHeight("-23")); // negative numbers
        assertFalse(Height.isValidHeight("-23.23")); // negative decimals
        assertFalse(Height.isValidHeight("as.23")); // inclusion of letters
        assertFalse(Height.isValidHeight("23.as")); // inclusion of letters
        assertFalse(Height.isValidHeight("23.a1")); // inclusion of letters
        assertFalse(Height.isValidHeight("23z1")); // inclusion of letters
        assertFalse(Height.isValidHeight("23,23")); // wrong symbols
        assertFalse(Height.isValidHeight("23|23")); // wrong symbols
        assertFalse(Height.isValidHeight("23/23")); // wrong symbols
        assertFalse(Height.isValidHeight("23./23")); // wrong symbols
        assertFalse(Height.isValidHeight("23/.23")); // wrong symbols
        assertFalse(Height.isValidHeight("2-3.23")); // wrong symbols
        assertFalse(Height.isValidHeight("23.2'3")); // wrong symbols

        // valid height
        assertTrue(Height.isValidHeight("0")); // single digit
        assertTrue(Height.isValidHeight("5")); // single digit
        assertTrue(Height.isValidHeight("456")); // numbers only
        assertTrue(Height.isValidHeight("1.2")); // numbers with decimal points
        assertTrue(Height.isValidHeight("45.23")); // numbers with decimal points
        assertTrue(Height.isValidHeight("0.1")); // numbers with decimal points and leading 0
        assertTrue(Height.isValidHeight("412343212384755.34573223138567385432")); // arbitrarily long numbers
    }
}
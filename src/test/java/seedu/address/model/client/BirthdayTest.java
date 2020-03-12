package seedu.address.model.client;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class BirthdayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plus(1, DAYS))); // birthday 1 day after current date
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plus(2, MONTHS))); //birthday 2 months after current date
        assertFalse(Birthday.isValidBirthday(LocalDate.now())); // birthday today

        // valid birthday
        assertTrue(Birthday.isValidBirthday(LocalDate.parse("01-01-1980",
                DateTimeFormatter.ofPattern("dd-MM-uuuu")))); // birthday in 01-01-1980
        assertTrue(Birthday.isValidBirthday(LocalDate.parse("01-01-1970",
                DateTimeFormatter.ofPattern("dd-MM-uuuu")))); //birthday in 01-01-1979

        assertTrue(new Birthday(LocalDate.parse("01-01-1980",
                DateTimeFormatter.ofPattern("dd-MM-uuuu"))).hashCode()
                == new Birthday(LocalDate.parse("01-01-1980",
                DateTimeFormatter.ofPattern("dd-MM-uuuu"))).hashCode());

        assertTrue(new Birthday(LocalDate.parse("01-01-1980",
                DateTimeFormatter.ofPattern("dd-MM-uuuu"))).toString()
                .equals("01-01-1980") == true);
    }
}

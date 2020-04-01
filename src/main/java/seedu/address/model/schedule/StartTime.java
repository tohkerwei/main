package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the start time of a schedule of a client. Guarantees: immutable; is
 * valid as declared in {@link #isValidTimingFormat(String)}
 */
public class StartTime extends Time {

    public final String value;
    public final int directTimeInt;

    /**
     * Constructs a {@code StartTime}.
     *
     * @param time A valid start time in the form HHMM
     */
    public StartTime(String time) {
        super(time);
        this.value = formatTime(time);
        this.directTimeInt = Integer.parseInt(time);
    }
}

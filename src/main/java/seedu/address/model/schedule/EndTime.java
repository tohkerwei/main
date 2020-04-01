package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the end time of a schedule of a client. Guarantees: immutable; is
 * valid as declared in {@link #isValidTimingFormat(String)}
 */
public class EndTime extends Time{

    public final String value;
    public final int directTimeInt;

    /**
     * Constructs a {@code EndTime}.
     *
     * @param time A valid end time in the form HHMM
     */
    public EndTime(String time) {
        super(time);
        this.value = formatTime(time);
        this.directTimeInt = Integer.parseInt(time);
    }

    @Override
    public EndTime clone() {
        String rawStartTime = this.value.substring(0,2) + this.value.substring(3);
        return new EndTime(rawStartTime);
    }

}


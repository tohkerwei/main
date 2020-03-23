package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Schedule;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     *
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");
        ArrayList<Schedule> scheduleList = new ArrayList<>();

        Index index = ParserUtil.parseIndex(keywords[0]);
        int numberOfSchedule = keywords.length / 3;

        for (int i = 0; i < numberOfSchedule; i += 3) {
            String day = ParserUtil.parseDay(keywords[i + 1]);
            String startTime = ParserUtil.parseTime(keywords[i + 2]);
            String endTime = ParserUtil.parseTime(keywords[i + 3]);
            if (!Schedule.isValidTimeFrame(startTime, endTime)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
            }
            Schedule newSchedule = new Schedule(day, startTime, endTime);
            scheduleList.add(newSchedule);
        }

        return new ScheduleCommand(index, scheduleList);
    }

}

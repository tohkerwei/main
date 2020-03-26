package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Day;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DAY,
                PREFIX_START_TIME, PREFIX_END_TIME);
        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_END_TIME, PREFIX_START_TIME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch(ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        ArrayList<Day> dayList = ParserUtil.parseDay(argMultimap.getAllValues(PREFIX_DAY));
        ArrayList<StartTime> startTimeList = ParserUtil.parseStartTime(argMultimap.getAllValues(PREFIX_START_TIME));
        ArrayList<EndTime> endTimeList = ParserUtil.parseEndTime(argMultimap.getAllValues(PREFIX_END_TIME));
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        for (int i = 0; i < dayList.size(); i++) {
            Day day = dayList.get(i);
            StartTime startTime = startTimeList.get(i);
            EndTime endTime = endTimeList.get(i);
            Schedule schedule = new Schedule(day, startTime, endTime);
            scheduleList.add(schedule);
            System.out.println(schedule);
        }

        return new ScheduleCommand(index, scheduleList);
    }

    /*
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

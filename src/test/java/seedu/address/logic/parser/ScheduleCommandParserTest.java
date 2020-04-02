package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.client.*;
import seedu.address.model.schedule.Day;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

import java.util.ArrayList;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

public class ScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DAY_MON, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + DAY_DESC_MON + TIME_DESC_START + TIME_DESC_END;

        ArrayList<Schedule> scheduleArrayList = new ArrayList<Schedule>();
        scheduleArrayList.add(Schedule.stringToSchedule("day/mon st/1200 et/1400"));
        ScheduleCommand expectedCommand = new ScheduleCommand(targetIndex, scheduleArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingOptionalFields_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = "" + targetIndex.getOneBased();

        ArrayList<Schedule> scheduleArrayList = new ArrayList<Schedule>();
        ScheduleCommand expectedCommand = new ScheduleCommand(targetIndex, scheduleArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DAY_DESC, Day.MESSAGE_CONSTRAINTS); // invalid day
        assertParseFailure(parser, "1" + INVALID_STARTTIME_DESC, StartTime.MESSAGE_CONSTRAINTS); // invalid start time
        assertParseFailure(parser, "1" + INVALID_ENDTIME_DESC, EndTime.MESSAGE_CONSTRAINTS); // invalid end time
    }
}

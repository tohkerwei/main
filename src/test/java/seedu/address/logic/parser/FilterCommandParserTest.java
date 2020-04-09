package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.client.Sport;
import seedu.address.model.client.TagAndSportContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTagAndSport_throwsParseException() {
        assertParseFailure(parser, "t/ s/", Sport.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TagAndSportContainsKeywordsPredicate(Arrays.asList("normal", "vegetarian"),
                        Arrays.asList("hockey", "sumo")));
        assertParseSuccess(parser, "t/normal vegetarian s/hockey sumo", expectedFilterCommand);
    }
}

package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.TagAndSportContainsKeywordsPredicate;

import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * @author tohkerwei
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_SPORT);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent() && !argMultimap.getValue(PREFIX_SPORT).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        List<String> sportKeywords = argMultimap.getAllValues(PREFIX_SPORT);

        return new FilterCommand(new TagAndSportContainsKeywordsPredicate(tagKeywords, sportKeywords));
    }
}

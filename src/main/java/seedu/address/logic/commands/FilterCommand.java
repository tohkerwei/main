package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.client.TagAndSportContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * @author tohkerwei
 * Filters and lists all clients in FitBiz whose tag or sports contains argument keywords for the respective parameters.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command{

    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters and display all clients whose tags or sports"
            + "contains any of the specified keywords for respective parameter (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Either tags or sports is optional. \n"
            + "Parameters: t/KEYWORD... s/KEYWORD...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + " vegetarian overweight handsome "
            + PREFIX_SPORT + "soccer dance basketball";

    private final TagAndSportContainsKeywordsPredicate predicate;

    public FilterCommand(TagAndSportContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}


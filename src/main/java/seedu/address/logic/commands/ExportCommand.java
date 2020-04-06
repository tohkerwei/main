package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.export.Exporter;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Lists all clients in FitBiz to the user.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the exercises of the client in view.";

    public static final String MESSAGE_SUCCESS = "Succesfully exported this client's exercises to '%s'.\n"
            + "Please check your /exports directory.";

    public static final String MESSAGE_CLIENT_NOT_IN_VIEW = "You currently do not have a client in view, ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasClientInView()) {
            throw new CommandException(MESSAGE_CLIENT_NOT_IN_VIEW);
        }

        Client clientInView = model.getClientInView();

        String fileName = Exporter.exportExercisesAsCsv(clientInView);

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }
}

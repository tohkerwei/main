package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;

/**
 * Adds the training schedule of the client
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the training schedule of the client, identified "
            + "by the index number used in the displayed client list. "
            + "The schedule should include the first 3 letters of the day, start and end time in 24 hour format, "
            + "in 30 minutes denominations. Multiple training schedule can be added to a client.\n"
            + "Parameters: INDEX (a positive integer) d/DAY st/START_TIME et/END_TIME [MORE_SCHEDULE]...\n"
            + "Example: " + COMMAND_WORD + " 1 d/mon st/1200 et/1400 d/fri st/1330 et/1500";

    public static final String MESSAGE_SUCCESS = "Training schedule has been added for %1$s";

    private final Index index;
    private final ArrayList<Schedule> toAdd;


    public ScheduleCommand(Index index, ArrayList<Schedule> toAdd) {
        requireNonNull(index);
        requireNonNull(toAdd);

        this.index = index;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient));
    }
}

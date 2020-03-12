package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.commons.core.index.Index;
import seedu.address.model.client.Client;
import seedu.address.model.client.Remark;

import java.util.List;

/**
 * Changes the remark of an existing client in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the clieny identified "
            + "by the index number used in the last client listing. "
            + "Existing remark will be overwritten by the input.\n" + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n" + "Example: " + COMMAND_WORD + " 1 " + PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Client: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Client: %1$s";

    private final Index index;
    private final Remark remark;

    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = new Client(clientToEdit.getName(), clientToEdit.getPhone(), clientToEdit.getEmail(),
                clientToEdit.getAddress(), remark, clientToEdit.getTags());

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage(editedClient));
    }

    /**
     * Generates a command execution success message based on whether the remark is
     * added to or removed from {@code clientToEdit}.
     */
    private String generateSuccessMessage(Client clientToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, clientToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index) && remark.equals(e.remark);
    }
}
package seedu.address.logic.autocomplete;

import java.util.ArrayList;

import seedu.address.commons.trie.SimilarWordsResult;
import seedu.address.commons.trie.Trie;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewCommand;

/**
 * This class contains the logic behind the auto complete function. It makes use
 * of {@code Trie} as the underlying data structure.
 */
public class AutoComplete {

    private static final String EMPTY_STRING = "";

    private final Trie trie;

    public AutoComplete() {
        trie = new Trie();
        addAllCommands();
    }

    public void addAllCommands() {
        trie.insert(AddCommand.COMMAND_WORD);
        trie.insert(ClearCommand.COMMAND_WORD);
        trie.insert(DeleteCommand.COMMAND_WORD);
        trie.insert(EditCommand.COMMAND_WORD);
        trie.insert(ExitCommand.COMMAND_WORD);
        trie.insert(FindCommand.COMMAND_WORD);
        trie.insert(HelpCommand.COMMAND_WORD);
        trie.insert(ListCommand.COMMAND_WORD);
        trie.insert(ViewCommand.COMMAND_WORD);
    }

    public SimilarWordsResult listAllSimilarCommands(String command) {
        return trie.listAllSimilarWords(command);
    }
}

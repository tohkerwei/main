package seedu.address.logic.autocomplete;

import java.util.ArrayList;

import javafx.scene.control.TextField;
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
import seedu.address.ui.ResultDisplay;

/**
 * This class contains the logic behind the auto complete function. It makes use
 * of {@code Trie} as the underlying data structure.
 */
public class AutoComplete {

    private static final int CARET_POSITION_INDEX = Integer.MAX_VALUE;
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE_STRING = " ";

    private final Trie trie;
    private final TextField commandTextField;
    private final ResultDisplay resultDisplay;

    public AutoComplete(TextField commandTextField, ResultDisplay resultDisplay) {
        trie = new Trie();
        this.commandTextField = commandTextField;
        this.resultDisplay = resultDisplay;
        addAllCommands();
    }

    private void addAllCommands() {
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

    public void execute() {
        String currCommand = commandTextField.getText();

        // command has already been completed
        if (currCommand.contains(WHITE_SPACE_STRING)) {
            return;
        }

        SimilarWordsResult similarWords = trie.listAllSimilarWords(currCommand);
        String longestPrefix = similarWords.longestPrefix;
        ArrayList<String> similarCommands = similarWords.list;
        if (similarCommands.isEmpty()) {
            resultDisplay.setFeedbackToUser("No commands found");
        } else if (similarCommands.size() == 1) {
            resultDisplay.setFeedbackToUser(EMPTY_STRING);
            commandTextField.setText(similarCommands.get(0));
            commandTextField.positionCaret(CARET_POSITION_INDEX);
        } else {
            commandTextField.setText(longestPrefix);
            commandTextField.positionCaret(CARET_POSITION_INDEX);
            resultDisplay.setFeedbackToUser("Commands found:\n" + similarCommands.toString());
        }
    }
}

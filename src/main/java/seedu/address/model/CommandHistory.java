package seedu.address.model;

import java.util.ArrayList;

import seedu.address.storage.CommandHistoryStorage;

/**
 * This represents the model of the command history.
 */
public class CommandHistory {

    private static final int START_INDEX = 0;
    private static final int MAX_HISTORY_SIZE = 100;
    private static final String EMPTY_STRING = "";

    private ArrayList<String> history;
    private int index;
    private CommandHistoryStorage commandHistoryStorage;

    /**
     * Default constructor for this class.
     */
    public CommandHistory() {
        this.commandHistoryStorage = new CommandHistoryStorage();
        initialiseCommandHistory();
    }

    /**
     * Initialises the history from the storage.
     */
    private void initialiseCommandHistory() {
        history = new ArrayList<String>(commandHistoryStorage.readCommandHistory());
        index = history.size();
    }

    private boolean isAtStart() {
        return index == START_INDEX;
    }

    private boolean isAtEnd() {
        return index == history.size();
    }

    private boolean hasNoHistory() {
        return history.size() == START_INDEX;
    }

    private String getMostRecentCommand() {
        return history.get(history.size() - 1);
    }

    private boolean isEmptyString(String toTest) {
        return toTest.equals(EMPTY_STRING);
    }

    /**
     * Returns true iff the parameter {@code toTest} is different from the most
     * recent command, or if no history exists.
     *
     * @param toTest parameter to test.
     * @return true iff toTest is different from the most recent command.
     */
    private boolean isSimilarToMostRecentCommand(String toTest) {
        if (hasNoHistory()) {
            return false; // no history means toTest is unique
        }
        return getMostRecentCommand().equals(toTest);
    }

    private boolean isAtMaxCapacity() {
        return history.size() >= MAX_HISTORY_SIZE;
    }

    /**
     * Adds the user input {@code command} String to this model and to the
     * {@code commandHistoryStorage}.
     *
     * @param command the user input command.
     */
    public void add(String command) {
        if (isEmptyString(command)) {
            return;
        }
        if (isSimilarToMostRecentCommand(command)) {
            return;
        }
        if (isAtMaxCapacity()) {
            history.remove(0);
        }

        history.add(command);
        index = history.size();
        commandHistoryStorage.saveCommandHistory(history);
    }

    public String getPrevCommand() {
        if (isAtStart()) {
            return hasNoHistory() ? EMPTY_STRING : history.get(index);
        }
        index--;
        return history.get(index);
    }

    public String getNextCommand() {
        if (isAtEnd()) {
            return EMPTY_STRING;
        }
        index++;
        return isAtEnd() ? EMPTY_STRING : history.get(index);
    }

    /**
     * Returns a copy of the history list. Any modifications done to this returned
     * list will not affect the list stored in this class.
     */
    public ArrayList<String> getHistory() {
        return new ArrayList<String>(history);
    }

    /**
     * Clears and resets the history and the index.
     */
    public void clearHistory() {
        history.clear();
        index = 0;
    }
}

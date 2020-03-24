package seedu.address.model;

import java.util.ArrayList;

import seedu.address.storage.CommandHistoryStorage;

public class CommandHistory {

    private static final int START_INDEX = 0;
    private static final int MAX_HISTORY_SIZE = 100;

    private ArrayList<String> history;
    private int index;
    private CommandHistoryStorage commandHistoryStorage;

    public CommandHistory() {
        this.commandHistoryStorage = new CommandHistoryStorage();
        initialiseCommandHistory();
    }

    public void initialiseCommandHistory() {
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
        return toTest.equals("");
    }

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
            return hasNoHistory() ? "" : history.get(index);
        }
        index--;
        return history.get(index);
    }

    public String getNextCommand() {
        if (isAtEnd()) {
            return "";
        }
        index++;
        return isAtEnd() ? "" : history.get(index);
    }
}
package seedu.address.logic.commands.history;

import java.util.ArrayList;

public class CommandHistory {

    private static final int START_INDEX = 0;

    private ArrayList<String> history = new ArrayList<>();
    private int index = 0;

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

    public void add(String command) {
        if (isEmptyString(command)) {
            return; // don't add
        }

        if (isSimilarToMostRecentCommand(command)) {
            return; // don't add
        }

        history.add(command);
        index = history.size();
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
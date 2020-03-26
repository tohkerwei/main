package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    private static final String EMPTY_STRING = "";
    private static final String VALID_COMMAND_1 = "edit-c 1 t/";
    private static final String VALID_COMMAND_2 = "edit-c 2 n/Nimar";
    private static final int LIST_SIZE_EMPTY = 0;
    private static final int LIST_SIZE_ONE = 1;
    private static final int LIST_SIZE_FIVE = 5;

    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor() {
        // no matter what, history must be initialised
        assertTrue(commandHistory.getHistory() != null);
    }

    @Test
    public void clearHistory() {
        commandHistory.clearHistory();
        assertEquals(Collections.emptyList(), commandHistory.getHistory());
    }

    @Test
    public void add_emptyString_nothingAdded() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(EMPTY_STRING);
        assertTrue(commandHistory.getHistory().size() == LIST_SIZE_EMPTY);
    }

    @Test
    public void add_similarValidCommandMultipleTimes_onlyOneAdded() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_1);
        assertTrue(commandHistory.getHistory().size() == LIST_SIZE_ONE);
    }

    @Test
    public void add_validCommandMultipleTimes_allAdded() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_2);
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_2);
        commandHistory.add(VALID_COMMAND_1);
        assertTrue(commandHistory.getHistory().size() == LIST_SIZE_FIVE);
    }

    @Test
    public void getNextCommand_emptyHistory_emptyStringReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        assertTrue(commandHistory.getNextCommand() == EMPTY_STRING);
    }

    @Test
    public void getPrevCommand_emptyHistory_emptyStringReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        assertTrue(commandHistory.getPrevCommand() == EMPTY_STRING);
    }

    @Test
    public void getNextCommand_nonEmptyHistory_emptyStringReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_2);
        assertTrue(commandHistory.getNextCommand() == EMPTY_STRING);
    }

    @Test
    public void getPrevCommand_oneItemHistory_sameStringReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        String command1 = commandHistory.getPrevCommand();
        String command2 = commandHistory.getPrevCommand();
        String command3 = commandHistory.getPrevCommand();
        String command4 = commandHistory.getPrevCommand();
        String command5 = commandHistory.getPrevCommand();
        assertTrue(command1 == VALID_COMMAND_1
                && command2 == VALID_COMMAND_1
                && command3 == VALID_COMMAND_1
                && command4 == VALID_COMMAND_1
                && command5 == VALID_COMMAND_1);
    }

    @Test
    public void getPrevCommand_twoItemsHistory_correctStringsReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_2);
        String shouldBeValidCommand2 = commandHistory.getPrevCommand();
        String shouldBeValidCommand1 = commandHistory.getPrevCommand();
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2
                && shouldBeValidCommand1 == VALID_COMMAND_1);
    }

    @Test
    public void getNextCommand_twoItemsHistoryAfterGetPrevCommand_correctStringReturned() {
        commandHistory.clearHistory(); // sanity check that history is empty
        commandHistory.add(VALID_COMMAND_1);
        commandHistory.add(VALID_COMMAND_2);
        commandHistory.getPrevCommand(); // returns VALID_COMMAND_2
        commandHistory.getPrevCommand(); // returns VALID_COMMAND_1
        String shouldBeValidCommand2 = commandHistory.getNextCommand();
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2);
    }
}

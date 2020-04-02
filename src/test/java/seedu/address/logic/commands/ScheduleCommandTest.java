package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ClientInView;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;

class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ClientInView());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ArrayList<Schedule> emptyScheduleList = new ArrayList<>();
        Client clientToSchedule = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_CLIENT, emptyScheduleList);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SUCCESS, clientToSchedule.getName().fullName,
                clientToSchedule.getScheduleList().toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new ClientInView());
        expectedModel.setClient(model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased()), clientToSchedule);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }
}

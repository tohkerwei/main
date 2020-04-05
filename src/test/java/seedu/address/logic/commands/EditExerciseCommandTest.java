package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExerciseCommandTestUtil.DESC_PUSHUP;
import static seedu.address.logic.commands.ExerciseCommandTestUtil.DESC_BENCH;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditExerciseCommand.EditExerciseDescriptor;
import seedu.address.model.ClientInView;
import seedu.address.model.FitBiz;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.EditExerciseDescriptorBuilder;
import seedu.address.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditExerciseCommand}.
 */
public class EditExerciseCommandTest {

    private Model model;
    private Client clientInView;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ClientInView());
        clientInView = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    }
    @Test
    public void execute_noClientInView_throwsCommandException() {
        Exercise editedExercise = new ExerciseBuilder().build();
        EditExerciseCommand.EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(editedExercise).build();
        EditExerciseCommand editExerciseCommand = new EditExerciseCommand(INDEX_FIRST_EXERCISE, descriptor);

        assertCommandFailure(editExerciseCommand, model, EditExerciseCommand.MESSAGE_CLIENT_NOT_IN_VIEW);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new ClientInView());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditExerciseCommand editExerciseCommand = new EditExerciseCommand(INDEX_FIRST_EXERCISE, new EditExerciseCommand.EditExerciseDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        String expectedMessage = String.format(EditExerciseCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new FitBiz(model.getAddressBook()), new UserPrefs(),
                new ClientInView());

        assertCommandSuccess(editExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setClientInView(clientInView);
        Exercise exerciseToedit = clientInView.getExerciseList().getExercise(INDEX_FIRST_EXERCISE);
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(DESC_PUSHUP).build();
        EditExerciseCommand editExerciseCommand = new EditExerciseCommand(INDEX_FIRST_EXERCISE, descriptor);

        String expectedMessage = String.format(EditExerciseCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, exerciseToedit);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ClientInView());
        Client alice = expectedModel.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        expectedModel.setClient(alice, ALICE_EDITED_EXERCISE);

        expectedModel.setClientInView(ALICE_EDITED_EXERCISE);

        assertCommandSuccess(editExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setClientInView(clientInView);

        Index outOfBoundIndex = Index.fromOneBased(clientInView.getExerciseList().size() + 1);
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(DESC_PUSHUP).build();
        EditExerciseCommand editExerciseCommand = new EditExerciseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editExerciseCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditExerciseCommand standardCommand = new EditExerciseCommand(INDEX_FIRST_EXERCISE, DESC_PUSHUP);

        // same values -> returns true
        EditExerciseCommand.EditExerciseDescriptor copyDescriptor = new EditExerciseDescriptor(DESC_PUSHUP);
        EditExerciseCommand commandWithSameValues = new EditExerciseCommand(INDEX_FIRST_EXERCISE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExerciseCommand(INDEX_SECOND_EXERCISE, DESC_BENCH)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditExerciseCommand(INDEX_FIRST_EXERCISE, DESC_BENCH)));
    }

}

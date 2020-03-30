package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;

/**
 * An UI class which wraps {@code ClientView} and {@code ExerciseListTable}.
 * It is updated when the clientInView updates or changes.
 * @author @yonggiee
 */
public class ClientViewDisplay {

    private ClientView clientView;
    private ExerciseListTable exerciseListTable;

    public ClientView getClientView(){
        return clientView;
    }

    public ExerciseListTable getExerciseListTable() {
        return exerciseListTable;
    }

    /**
     * Updates the client in {@code ClientView}.
     */
    public void updateClientView(Client client) {
        requireNonNull(client);
        this.clientView = new ClientView(client);
    }

    /**
     * Updates the exercise list in {@code ExerciseListTable}.
     */
    public void updateExerciseListTable(ObservableList<Exercise> exerciseList) {
        requireNonNull(exerciseList);
        this.exerciseListTable = new ExerciseListTable(exerciseList);
    }

    /**
     * Updates the client to be shown on {@code ClientViewDisplay}.
     */
    public void update(Client client) {
        updateClientView(client);
        updateExerciseListTable(client.getExerciseList().asUnmodifiableObservableList());
    }
}

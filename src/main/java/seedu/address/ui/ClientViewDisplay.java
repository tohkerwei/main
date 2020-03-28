package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javax.xml.namespace.QName;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;

/**
 * A ui for which shows both detailed client details and exercises done by client.
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

    public void updateClientView(Client client) {
        this.clientView = new ClientView(client);
    }

    public void updateExerciseListTable(ObservableList<Exercise> exerciseList) {
        this.exerciseListTable = new ExerciseListTable(exerciseList);
    }

    public void update(Client client) {
        updateClientView(client);
        updateExerciseListTable(client.getExerciseList().asUnmodifiableObservableList());
    }
}

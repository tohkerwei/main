package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;

/**
 * A ui for which shows both detailed client details and exercises done by client.
 */
public class ClientViewDisplay {

    private final ClientView clientView;
    private ExerciseListPanel exerciseListPanel;

    public ClientViewDisplay(ClientView clientView, ExerciseListPanel exerciseListPanel){
        requireNonNull(clientView);
        this.clientView = clientView;
        this.exerciseListPanel = exerciseListPanel;
    }

    public ClientView getClientView(){
        return clientView;
    }

    public ExerciseListPanel getExerciseListPanel() {
        return exerciseListPanel;
    }

    public void updateClientView(Client client) {
        clientView.update(client);
        ObservableList<Exercise> e = client.getExerciseList().asUnmodifiableObservableList();
        this.exerciseListPanel = new ExerciseListPanel(e);
    }

    public void updateExerciseList(ObservableList<Exercise> exerciseList) {
        exerciseListPanel.update(exerciseList);
    }

}

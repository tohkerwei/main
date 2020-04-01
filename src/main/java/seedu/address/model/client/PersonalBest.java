package seedu.address.model.client;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.statistics.PersonalBestFinder;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;

public class PersonalBest {
    private HashMap<ExerciseName, Exercise> personalBestTable;
    private ObservableList<Exercise> personalBestList = FXCollections.observableArrayList();

    public void setPersonalBest(Client client) {
        personalBestTable = PersonalBestFinder.createPbList(client);
        personalBestList.addAll(personalBestTable.values());
    }

    @Override
    public String toString() {
        return personalBestList.toString();
    }
}

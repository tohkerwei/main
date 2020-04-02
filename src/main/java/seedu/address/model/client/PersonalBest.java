package seedu.address.model.client;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.statistics.PersonalBestFinder;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;

/**
 * Represents the personal bests of a client's exercises in FitBiz.
 */
public class PersonalBest {
    private HashMap<ExerciseName, Exercise> personalBestTable;
    private ObservableList<Exercise> personalBestList = FXCollections.observableArrayList();

    public void setPersonalBest(Client client) {
        personalBestTable = PersonalBestFinder.createPbList(client);
        personalBestList.clear();
        personalBestList.addAll(personalBestTable.values());
    }

    @Override
    public String toString() {
        String toPrint = "Personal Best \n";
        for (Exercise e : personalBestList) {
            toPrint += e.toString() + "\n";
        }
        return toPrint;
    }
}

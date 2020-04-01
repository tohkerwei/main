package seedu.address.model.client;

import java.util.HashMap;

import seedu.address.logic.statistics.PersonalBestFinder;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;

public class PersonalBest {
    private HashMap<ExerciseName, Exercise> pbTable;

    public void setPersonalBest(Client client) {
        pbTable = PersonalBestFinder.createPbList(client);
    }

    @Override
    public String toString() {
        return pbTable.toString();
    }
}

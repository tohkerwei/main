package seedu.address.model.exercise;

import java.util.HashMap;

import seedu.address.logic.PersonalBestFinder;
import seedu.address.model.client.Client;

public class PersonalBest {
    private HashMap<ExerciseName, Exercise> pbTable;

    public PersonalBest(HashMap<ExerciseName, Exercise> pbTable) {
        this.pbTable = pbTable;
    }

    public void createPbList(Client client) {
        pbTable = PersonalBestFinder.createPbList(client);
    }

    @Override
    public String toString() {
        return pbTable.toString();
    }
}

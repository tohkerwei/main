package seedu.address.model.exercise;

import java.util.HashMap;

import seedu.address.model.client.Client;

public class PersonalBest {
    HashMap<ExerciseName, Exercise> pbTable;
    Client client;

    public PersonalBest(HashMap<ExerciseName, Exercise> pbTable, Client client) {
        this.pbTable = pbTable;
        this.client = client;
    }

    @Override
    public String toString() {
        return pbTable.toString();
    }
}
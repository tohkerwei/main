package seedu.address.logic;

import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;
import seedu.address.model.exercise.UniqueExerciseList;

import java.util.HashMap;

public class PersonalBestFinder {
    
    // create a new hashmap of personal bests when view-c is called
    public HashMap<ExerciseName, Exercise> createPbList(Client clientInView) {
        UniqueExerciseList exerciseList = clientInView.getExerciseList();        
        HashMap<ExerciseName, Exercise> pbTable = new HashMap<ExerciseName, Exercise>();
        ExerciseName name;
        int weight;
        int pbWeight;

        for (Exercise ex : exerciseList) {
            name = ex.getExerciseName();
            weight = Integer.parseInt(ex.getExerciseWeight().toString());

            if (pbTable.containsKey(name)) {
                pbWeight = Integer.parseInt(pbTable.get(name).getExerciseWeight().toString());
                
                if (pbWeight < weight) {
                    pbTable.put(name, ex);
                }
            } else {
                pbTable.put(name, ex);
            }
        }
        return pbTable;
    }
    
    // update personal best hashmap if view-c is called
    public void updatePersonalBest() {

    }
}
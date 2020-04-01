package seedu.address.logic;

import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;
import seedu.address.model.exercise.UniqueExerciseList;

import java.util.HashMap;

public class PersonalBestFinder {

    // create a new hashmap of personal bests when view-c or add-e is called
    public static HashMap<ExerciseName, Exercise> createPbList(Client clientInView) {
        UniqueExerciseList exerciseList = clientInView.getExerciseList();        
        HashMap<ExerciseName, Exercise> pbTable = new HashMap<ExerciseName, Exercise>();

        for (Exercise ex : exerciseList) {
            ExerciseName name = ex.getExerciseName();
            int weight = Integer.parseInt(ex.getExerciseWeight().toString());

            if (pbTable.containsKey(name)) {
                int pbWeight = Integer.parseInt(pbTable.get(name).getExerciseWeight().toString());

                if (pbWeight < weight) {
                    pbTable.put(name, ex);
                } else if (pbWeight == weight) {
                    
                }
            } else {
                pbTable.put(name, ex);
            }
        }
        return pbTable;
    }

}

package seedu.address.logic;

import java.util.HashMap;

import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;
import seedu.address.model.exercise.UniqueExerciseList;

public class PersonalBestFinder {

    // create a new hashmap of personal bests when view-c or add-e is called
    public static HashMap<ExerciseName, Exercise> createPbList(Client clientInView) {
        UniqueExerciseList exerciseList = clientInView.getExerciseList();
        HashMap<ExerciseName, Exercise> pbTable = new HashMap<ExerciseName, Exercise>();

        for (Exercise ex : exerciseList) {
            ExerciseName name = ex.getExerciseName();
            int weight = convertToInt(ex.getExerciseWeight().toString());
            int reps = convertToInt(ex.getExerciseReps().toString());

            if (pbTable.containsKey(name)) {
                int pbWeight = convertToInt(pbTable.get(name).getExerciseWeight().toString());
                int pbReps = convertToInt(pbTable.get(name).getExerciseReps().toString());

                if (pbWeight < weight) {
                    pbTable.put(name, ex);

                } else if (pbWeight == weight) {
                    if (pbReps < reps) {
                        pbTable.put(name, ex);
                    }
                }
            } else if (hasWeightOrReps(ex)) { // has one or both
                pbTable.put(name, ex);
            }
        }
        return pbTable;
    }

    private static int convertToInt(String string) {
        int result = 0;
        if (!string.equals("")) {
            result = Integer.parseInt(string);
        }
        return result;
    }

    private static boolean hasWeightOrReps(Exercise ex) {
        boolean hasWeight = !ex.getExerciseWeight().toString().equals("");
        boolean hasReps = !ex.getExerciseReps().toString().equals("");
        return hasWeight || hasReps;
    }

}

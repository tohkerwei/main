package seedu.address.logic.export;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.exercise.Exercise;

public class Exporter {

    public static String exercisesAsCsvString(List<Exercise> exercises) throws CommandException {
        if (exercises.isEmpty()) {
            throw new CommandException("This client does not have any exercises.");
        }

        String headers = "Date,Exercise Name,Reps,Weights,Sets\n";
       
        String content = headers;
        for (Exercise e : exercises) {
            String dateString = e.getExerciseDate().displayValue;
            String nameString = e.getExerciseName().value;
            String repsString = e.getExerciseReps().value;
            String weightString = e.getExerciseWeight().value;
            String setsString = e.getExerciseSets().value;
            String line = String.format("%s,%s,%s,%s,%s\n", dateString, nameString, repsString,
                    weightString, setsString);
            content += line;
        }

        return content;
    }

}

package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an exercise done by a client. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Exercise {

    public final ExerciseName exerciseName;
    public final Reps reps;
    public final Sets sets;
    public final ExerciseWeight exerciseWeight;

    public Exercise(ExerciseName exerciseName, Reps reps, Sets sets, ExerciseWeight exerciseWeight) {
        requireAllNonNull(exerciseName);
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.exerciseWeight = exerciseWeight;
    }

    public ExerciseName getExerciseName() {
        return exerciseName;
    }

    public Reps getReps() {
        return reps;
    }

    public Sets getSets() {
        return sets;
    }

    public ExerciseWeight getExerciseWeight() {
        return exerciseWeight;
    }

    // /**
    //  * Returns true if both clients of the same name have at least one other
    //  * identity field that is the same. This defines a weaker notion of equality
    //  * between two clients.
    //  */
    // public boolean isSameClient(Client otherClient) {
    //     if (otherClient == this) {
    //         return true;
    //     }

    //     return otherClient != null && otherClient.getName().equals(getName())
    //             && (otherClient.getPhone().equals(getPhone()) || otherClient.getEmail().equals(getEmail()));
    // }

    /**
     * Returns true if both exercise have the attribute values. 
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.getExerciseName().equals(getExerciseName()) && otherExercise.getReps().equals(getReps())
                && otherExercise.getSets().equals(getSets()) && otherExercise.getExerciseWeight().equals(getExerciseWeight()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(exerciseName, reps, sets, exerciseWeight);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Exercise Name: ")
            .append(getExerciseName())
            .append(" Sets: ")
            .append(getSets())
            .append(" Reps: ")
            .append(getReps())
            .append(" Exercise Weight: ")
            .append(getExerciseWeight());;
        return builder.toString();
    }

}

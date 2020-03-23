package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exercise.Exercise;

/**
 * Adds an exercise done by a client in FitBiz.
 */
public class AddExerciseCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise done by a client in FitBiz. " + "Parameters: "
            + PREFIX_NAME + "EXERCISE NAME " + PREFIX_REPS + "REPS " + PREFIX_EXERCISE_WEIGHT + "EXERCISE WEIGHT "
            + PREFIX_SETS + "SETS\n" + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "bench press " + PREFIX_REPS + "12 "
            + PREFIX_EXERCISE_WEIGHT + "50 "
            + PREFIX_SETS + "8";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in FitBiz";

    private final Exercise toAdd;

    /**
     * Creates an AddExerciseCommand to add the specified {@code Exercise}
     */
    public AddExerciseCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExerciseCommand // instanceof handles nulls
                        && toAdd.equals(((AddExerciseCommand) other).toAdd));
    }
}

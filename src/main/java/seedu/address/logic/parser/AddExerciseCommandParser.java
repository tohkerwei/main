package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETS;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddExerciseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseName;
import seedu.address.model.exercise.ExerciseWeight;
import seedu.address.model.exercise.Reps;
import seedu.address.model.exercise.Sets;

/**
 * Parses input arguments and creates a new AddExerciseCommand object
 */
public class AddExerciseCommandParser implements Parser<AddExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExerciseCommand
     * and returns an AddExerciseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExerciseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_REPS, PREFIX_EXERCISE_WEIGHT, PREFIX_SETS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExerciseCommand.MESSAGE_USAGE));
        }

        ExerciseName name = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get());
        Optional<String> repsString = argMultimap.getValue(PREFIX_REPS);
        Reps reps = repsString.isPresent() ? ParserUtil.parseReps(argMultimap.getValue(PREFIX_REPS).get())
                : new Reps("");
        Optional<String> exerciseWeightString = argMultimap.getValue(PREFIX_REPS);
        ExerciseWeight exerciseWeight = exerciseWeightString.isPresent()
                ? ParserUtil.parseExerciseWeight(argMultimap.getValue(PREFIX_EXERCISE_WEIGHT).get())
                : new ExerciseWeight("");
        Optional<String> setsString = argMultimap.getValue(PREFIX_REPS);
        Sets sets = setsString.isPresent() ? ParserUtil.parseSets(argMultimap.getValue(PREFIX_SETS).get())
                : new Sets("");
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Exercise exercise = new Exercise(name, reps, sets, exerciseWeight, date);

        return new AddExerciseCommand(exercise);
    }

    /*
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_EMPTY_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.logic.commands.FilterCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap;
        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_EMAIL);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), FilterCommand.MESSAGE_USAGE));
        }

        // Initializing all the arguments at the beginning.
        String trimmedNameArg = getTrimmedPredicateArg(argMultimap, PREFIX_NAME);
        String trimmedSidArg = getTrimmedPredicateArg(argMultimap, PREFIX_STUDENT_ID);
        String trimmedCidArg = getTrimmedPredicateArg(argMultimap, PREFIX_CLASS_ID);
        String trimmedEmailArg = getTrimmedPredicateArg(argMultimap, PREFIX_EMAIL);
        QueryStudentDescriptor queryStudentDescriptor = getQueryStudentDescriptor(trimmedNameArg,
                                                                                  trimmedSidArg,
                                                                                  trimmedCidArg,
                                                                                  trimmedEmailArg);

        StudentDetailContainsQueryPredicate predicate = new StudentDetailContainsQueryPredicate(queryStudentDescriptor);
        return new FilterCommand(predicate);
    }

    /**
     * Retrieves the trimmed predicate argument from the argument Multimap.
     * If argument does not exist in argument Multimap, returns `null` instead.
     *
     * @param argMultimap the map to get the argument from
     * @param predicate the specified predicate
     * @return the trimmed predicate argument
     * @throws ParseException if the trimmed predicate argument is empty
     */
    private String getTrimmedPredicateArg(ArgumentMultimap argMultimap, Prefix predicate) throws ParseException {
        String trimmedPredicateArg = null;

        if (argMultimap.getValue(predicate).isPresent()) {
            trimmedPredicateArg = argMultimap.getValue(predicate).get().trim();
            if (trimmedPredicateArg.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
            }
        }
        return trimmedPredicateArg;
    }

    /**
     * Gets the query student descriptor based on the arguments specified.
     *
     * @param nameArg the name argument
     * @param sidArg the student id argument
     * @param cidArg the class id argument
     * @param emailArg the email argument
     * @return the QueryStudentDescriptor
     * @throws ParseException if no field is specified to be queried
     */
    private QueryStudentDescriptor getQueryStudentDescriptor(
            String nameArg, String sidArg, String cidArg, String emailArg) throws ParseException {
        QueryStudentDescriptor queryStudentDescriptor =
                new QueryStudentDescriptor(nameArg, sidArg, cidArg, emailArg);

        if (!queryStudentDescriptor.isAnyFieldToBeQueried()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        return queryStudentDescriptor;
    }
}

package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.stream.Stream;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_EMAIL);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), AddCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENT, AddCommand.MESSAGE_USAGE));
        }

        Student student = createStudent(argMultimap);
        return new AddCommand(student);
    }

    private Student createStudent(ArgumentMultimap argMultimap) throws ParseException {
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElse(null));
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).orElse(null));
        ClassId classId = ParserUtil.parseClassId(argMultimap.getValue(PREFIX_CLASS_ID).orElse(null));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(null));
        return new Student(name, studentId, classId, email);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

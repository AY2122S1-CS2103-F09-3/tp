package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;
        Index index;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID, PREFIX_EMAIL,
                            PREFIX_LAB_NUM, PREFIX_LAB_RESULT);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), EditCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editstudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editstudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editstudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap
                    .getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_ID).isPresent()) {
            editstudentDescriptor.setClassId(ParserUtil.parseClassId(argMultimap.getValue(PREFIX_CLASS_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editstudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_LAB_NUM).isPresent() && argMultimap.getValue(PREFIX_LAB_RESULT).isPresent()) {
            int labNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null));
            int result =(Integer) ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_RESULT).orElse(null));
            Lab labResult = new Lab(labNum);
            editstudentDescriptor.setLab(labResult, result);
        }

        if (!editstudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editstudentDescriptor);
    }

}

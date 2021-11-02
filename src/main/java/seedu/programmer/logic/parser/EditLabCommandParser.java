package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_TEMPLATE;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.commands.EditLabCommand.MESSAGE_ARGUMENT_SHOULD_BE_SPECIFIED;
import static seedu.programmer.logic.commands.EditLabCommand.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.commands.EditLabCommand.MESSAGE_MISSING_LAB_TO_BE_EDITED;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NEW_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.stream.Stream;

import seedu.programmer.logic.commands.EditLabCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;


/**
 * Parses input arguments and creates a new CreateLabResultCommand object
 */
public class EditLabCommandParser implements Parser<EditLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLabCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap;
        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_LAB_NUM, PREFIX_LAB_NEW_LAB_NUM, PREFIX_LAB_TOTAL);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), EditLabCommand.MESSAGE_USAGE));
        }

        try {
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            if (!arePrefixesPresent(argMultimap, PREFIX_LAB_NUM)) {
                throw new ParseException(MESSAGE_MISSING_LAB_TO_BE_EDITED);
            }

            if (argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).isPresent()
                    && argMultimap.getValue(PREFIX_LAB_TOTAL).isPresent()) {
                // Provided new lab number and total score
                int labNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null));
                int newLabNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).orElse(null));
                int total = ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
                Lab labResult = new Lab(labNum);
                return new EditLabCommand(labResult, newLabNum, total);
            } else if (argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).isPresent()) {
                // Provided new lab number only
                int labNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null));
                int newLabNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NEW_LAB_NUM).orElse(null));
                Lab labResult = new Lab(labNum);
                return new EditLabCommand(labResult, newLabNum);
            } else if (argMultimap.getValue(PREFIX_LAB_TOTAL).isPresent()) {
                int labNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null));
                int total = ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
                Lab labResult = new Lab(labNum);
                return new EditLabCommand(labResult, total);
            } else {
                throw new ParseException(MESSAGE_ARGUMENT_SHOULD_BE_SPECIFIED);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_TEMPLATE, pe.getMessage(), EditLabCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

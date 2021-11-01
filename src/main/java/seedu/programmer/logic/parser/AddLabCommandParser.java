package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.stream.Stream;

import seedu.programmer.logic.commands.AddLabCommand;
import seedu.programmer.logic.parser.exceptions.InvalidArgFlagsException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;


/**
 * Parses input arguments and creates a new CreateLabResultCommand object
 */
public class AddLabCommandParser implements Parser<AddLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLabCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LAB_NUM, PREFIX_LAB_TOTAL);
        } catch (InvalidArgFlagsException e) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, e.getMessage(), AddLabCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_LAB_NUM, PREFIX_LAB_TOTAL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENT, AddLabCommand.MESSAGE_USAGE));
        }

        int labNum = ParserUtil.parseLabNum(argMultimap.getValue(PREFIX_LAB_NUM).orElse(null));
        int total = ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
        Lab labResult = new Lab(labNum, total);
        return new AddLabCommand(labResult);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

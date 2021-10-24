package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.AddLabCommand;
import seedu.programmer.logic.commands.Command;
import seedu.programmer.logic.commands.DeleteCommand;
import seedu.programmer.logic.commands.DeleteLabCommand;
import seedu.programmer.logic.commands.DownloadCommand;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditLabCommand;
import seedu.programmer.logic.commands.ExitCommand;
import seedu.programmer.logic.commands.FillCommand;
import seedu.programmer.logic.commands.FilterCommand;
import seedu.programmer.logic.commands.HelpCommand;
import seedu.programmer.logic.commands.ListCommand;
import seedu.programmer.logic.commands.PurgeCommand;
import seedu.programmer.logic.commands.ShowCommand;
import seedu.programmer.logic.commands.UploadCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ProgrammerErrorParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(format(userInput));
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case PurgeCommand.COMMAND_WORD:
            return new PurgeCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FillCommand.COMMAND_WORD:
            return new FillCommand();

        case AddLabCommand.COMMAND_WORD:
            return new AddLabCommandParser().parse(arguments);

        case DeleteLabCommand.COMMAND_WORD:
            return new DeleteLabCommandParser().parse(arguments);

        case EditLabCommand.COMMAND_WORD:
            return new EditLabCommandParser().parse(arguments);

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case DownloadCommand.COMMAND_WORD:
            return new DownloadCommand();

        case UploadCommand.COMMAND_WORD:
            return new UploadCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Returns a formatted string by adding a whitespace behind {@code str}.
     * @param str is the String to be formatted.
     * @return the formatted string.
     */
    private String format(String str) {
        return str + " ";
    }

}

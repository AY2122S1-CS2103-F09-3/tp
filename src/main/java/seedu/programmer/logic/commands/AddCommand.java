package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Student;

/**
 * Adds a student to ProgrammerError.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the ProgrammerError.\n"
            + "Parameters: "
            + PREFIX_NAME + "<NAME> "
            + PREFIX_STUDENT_ID + "<STUDENT_ID> "
            + PREFIX_CLASS_ID + "<CLASS_ID> "
            + PREFIX_EMAIL + "<EMAIL>\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STUDENT_ID + "A0121234H "
            + PREFIX_CLASS_ID + "B01 "
            + PREFIX_EMAIL + "e0518441@u.nus.edu";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the ProgrammerError";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        model.setSelectedStudent(toAdd);
        model.setSelectedLabs(toAdd.getLabList());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

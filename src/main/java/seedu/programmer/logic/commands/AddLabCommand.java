package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.List;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "addlab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to all the students in the list. "
            + "Parameters: "
            + PREFIX_LAB_NUM + "Lab Number"
            + PREFIX_LAB_TOTAL + "Total Score"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_LAB_NUM + "1 "
            + PREFIX_LAB_TOTAL + "20";

    public static final String MESSAGE_ADD_LAB_SUCCESS = "Lab Added: %1$s";

    public static final String MESSAGE_LAB_ALREADY_EXISTS = "Lab Already Exists: %1$s";

    public static final String LAB_SCORE_MESSAGE_CONSTRAINTS = "The lab total score should be a positive value.";


    private final Lab result;

    /**
     * @param result the lab result to be added.
     * */
    public AddLabCommand(Lab result) {
        requireNonNull(result);
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Gets the last filtered list displayed
        List<Student> lastShownList = model.getFilteredStudentList();
        boolean exists = false;
        if (result.getTotalScore() < 0.0) {
            throw new CommandException(LAB_SCORE_MESSAGE_CONSTRAINTS);
        }
        for (Student std: lastShownList) {
            Student target = std;
            if (!target.addLabResult(this.result)) {
                exists = true;
            }
            model.setStudent(target, std);

        }
        if (exists) {
            throw new CommandException(String.format(MESSAGE_LAB_ALREADY_EXISTS, result));
        } else {
            return new CommandResult(String.format(MESSAGE_ADD_LAB_SUCCESS, result));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand// instanceof handles nulls
                && result.equals(((AddLabCommand) other).result)); // state check
    }
}


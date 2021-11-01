package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Student;

/**
 * Shows the student's particular and lab results at the given index
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the student's particular and lab results "
            + "at the given index.\n"
            + "Parameter: <INDEX> (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_STUDENT_SUCCESS = "Showed %s's lab results on the side panel";

    private final Index targetIndex;

    public ShowCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public ShowCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToShow = lastShownList.get(targetIndex.getZeroBased());
        model.setSelectedStudent(studentToShow);
        model.setSelectedLabs(studentToShow.getLabList());
        String feedbackToUser = String.format(MESSAGE_SHOW_STUDENT_SUCCESS, studentToShow.getName());
        return new ShowCommandResult(feedbackToUser, studentToShow);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && targetIndex.equals(((ShowCommand) other).targetIndex)); // state check
    }
}

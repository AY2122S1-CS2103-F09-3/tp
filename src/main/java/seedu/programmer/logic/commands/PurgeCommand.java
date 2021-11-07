package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.programmer.model.Model;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.student.Student;

/**
 * Purges ProgrammerError.
 */
public class PurgeCommand extends Command {

    public static final String COMMAND_WORD = "purge";
    public static final String MESSAGE_SUCCESS = "ProgrammerError has been purged of data!";
    public static final String MESSAGE_FAIL = "There is no data to purge!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isLastShownListEmpty(model)) {
            return new CommandResult(MESSAGE_FAIL);
        }
        model.setProgrammerError(new ProgrammerError());
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.clearSelectedInformation();
        model.clearLabsTracker();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Parses ProgrammerError and check if its empty.
     * @param model Input the model/data.
     * @return Whether the list is empty or not.
     */
    public boolean isLastShownListEmpty(Model model) {
        List<Student> lastShownList = model.getFilteredStudentList();
        return lastShownList.size() == 0;
    }
}

package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.programmer.model.Model;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.util.SampleDataUtil;

/**
 * Fills the model with sample data if there is no data.
 */
public class FillCommand extends Command {

    public static final String COMMAND_WORD = "fill";
    public static final String MESSAGE_SUCCESS = "ProgrammerError has been filled with sample data!";
    public static final String MESSAGE_FAIL = "There is existing data! Please purge the "
                                                    + "existing data to import sample data";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isLastShownListEmpty(model)) {
            model.setProgrammerError(SampleDataUtil.getSampleProgrammerError());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.setLabsTracker(model.getFilteredStudentList().get(0).getFreshLabList());
        return new CommandResult(MESSAGE_FAIL);
    }

    /**
     * Parses the ProgrammerError and check if its empty.
     * @param model Input the model/data.
     * @return Whether the list is empty or not.
     */
    public boolean isLastShownListEmpty(Model model) {
        List<Student> lastShownList = model.getFilteredStudentList();
        return lastShownList.size() == 0;
    }
}

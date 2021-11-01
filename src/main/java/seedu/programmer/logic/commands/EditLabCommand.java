package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NEW_LAB_NUM;
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
public class EditLabCommand extends Command {

    public static final String COMMAND_WORD = "editlab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a lab number and/or total score.\n"
            + "Parameters: "
            + PREFIX_LAB_NUM + "<LAB_NUMBER> "
            + "[" + PREFIX_LAB_NEW_LAB_NUM + " <NEW_LAB_NUMBER>] "
            + "[" + PREFIX_LAB_TOTAL + " <NEW_TOTAL_SCORE>]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB_NUM + "1 "
            + PREFIX_LAB_NEW_LAB_NUM + "2 "
            + PREFIX_LAB_TOTAL + "15";

    public static final String MESSAGE_EDIT_LAB_SUCCESS = "Updated %1$s!";

    public static final String MESSAGE_SCORE_SHOULD_BE_POSITIVE = "The lab total score %f should be a positive value.";

    public static final String MESSAGE_LAB_ALREADY_EXISTS = "Lab %d already exists.";

    public static final String MESSAGE_LAB_NOT_EXISTS = "Lab %d doesn't exist.";

    private final int newLabNum;
    private final int total;
    private final Lab original;

    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, int newLabNum, int total) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = total;
    }
    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, Integer total) {
        this.original = original;
        this.total = total;
        this.newLabNum = 0;
    }

    /**
     * @param original the lab to be edited.
     * @param newLabNum the new lab number
     * */
    public EditLabCommand(Lab original, int newLabNum) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = original.getTotalScore();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Lab newLab = new Lab(newLabNum);
        if (lastShownList.get(0).getLabList().contains(newLab)) {
            throw new CommandException(String.format(MESSAGE_LAB_ALREADY_EXISTS, newLab.getLabNum()));
        }
        for (Student std : lastShownList) {
            Student editedStd = std;
            if (total < 0) {
                throw new CommandException(MESSAGE_SCORE_SHOULD_BE_POSITIVE);
            } else if (!std.getLabList().contains(original)) {
                throw new CommandException(String.format(MESSAGE_LAB_NOT_EXISTS, original.getLabNum()));
            } else {
                editedStd.editLabInfo(original, newLabNum, total);
                model.setStudent(std, editedStd);
            }
        }
        if (!model.getSelectedInformation().isEmpty()) {
            Student selectedStudent = model.getSelectedStudent().copy();
            model.setSelectedStudent(selectedStudent);
            model.setSelectedLabs(selectedStudent.getLabList());
        }

        return new CommandResult(String.format(MESSAGE_EDIT_LAB_SUCCESS, original));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditLabCommand// instanceof handles nulls
                && original.equals(((EditLabCommand) other).original)); // state check
    }
}

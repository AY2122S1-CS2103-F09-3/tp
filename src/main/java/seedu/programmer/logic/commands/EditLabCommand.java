package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NEW_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.List;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabTotal;
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
    public static final String MESSAGE_LAB_ALREADY_EXISTS = "%1$s already exists.";
    public static final String MESSAGE_LAB_NOT_EXISTS = "%1$s doesn't exist.";
    public static final String MESSAGE_ARGUMENT_SHOULD_BE_SPECIFIED =
            "Kindly specify if you want to edit the lab number and/or the total score.";
    public static final String MESSAGE_MISSING_LAB_TO_BE_EDITED =
            "Kindly specify the lab number that you would like to edit using the " + PREFIX_LAB_NUM + "flag.\n%1$s";

    private final LabNum newLabNum;
    private final LabTotal total;
    private final Lab original;

    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, LabNum newLabNum, LabTotal total) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = total;
    }
    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, LabTotal total) {
        this.original = original;
        this.total = total;
        this.newLabNum = new LabNum(0);
    }

    /**
     * @param original the lab to be edited.
     * @param newLabNum the new lab number
     * */
    public EditLabCommand(Lab original, LabNum newLabNum) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = original.getLabTotal();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Lab newLab = new Lab(newLabNum);
        if (lastShownList.get(0).getLabList().contains(newLab)) {
            throw new CommandException(String.format(MESSAGE_LAB_ALREADY_EXISTS, newLab));
        }
        for (Student std : lastShownList) {
            Student editedStd = std;
            if (total != null && total.getLabTotal() < 0.0) {
                throw new CommandException(String.format(MESSAGE_SCORE_SHOULD_BE_POSITIVE, total));
            } else if (!std.getLabList().contains(original)) {
                throw new CommandException(String.format(MESSAGE_LAB_NOT_EXISTS, original));
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

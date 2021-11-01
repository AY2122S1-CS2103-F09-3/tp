package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.commons.util.CollectionUtil;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * Edits the details of an existing student in ProgrammerError.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "<NAME>] "
            + "[" + PREFIX_STUDENT_ID + "<STUDENT_ID>] "
            + "[" + PREFIX_CLASS_ID + "<CLASS_ID>] "
            + "[" + PREFIX_EMAIL + "<EMAIL>] "
            + "[" + PREFIX_LAB_NUM + "<LAB_NUMBER> " + PREFIX_LAB_RESULT + "<LAB_SCORE>]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STUDENT_ID + "A0121234H "
            + PREFIX_CLASS_ID + "B01";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the ProgrammerError.";
    public static final String MESSAGE_INVALID_LAB_NUMBER = "The lab does not exist!";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.setSelectedStudentWrapper(editedStudent);
        model.setSelectedLabs(editedStudent.getLabList());
        return new EditCommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent), editedStudent);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor)
            throws CommandException {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        StudentId updateStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        ClassId updatedClassId = editStudentDescriptor.getClassId().orElse(studentToEdit.getClassId());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Lab updatedLab = editStudentDescriptor.getLab().orElse(null);
        int updatedResult = 0;
        if (updatedLab != null) {
            updatedResult = editStudentDescriptor.getResult().orElse(0);
            int labNum = updatedLab.getLabNum();
            int currTotalScore;
            try {
                currTotalScore = studentToEdit.getLab(labNum).getTotalScore();
            } catch (NullPointerException e) { //when getLab does not find anything
                throw new CommandException(MESSAGE_INVALID_LAB_NUMBER);
            }
            updatedLab.updateTotal(currTotalScore);
        }

        if (updatedLab != null && updatedResult != 0) {
            System.out.println(updatedResult);
            studentToEdit.editLabScore(updatedLab, updatedResult);
            ObservableList<Lab> updatedList = studentToEdit.getLabList();
            return new Student(updatedName, updateStudentId, updatedClassId, updatedEmail, updatedList);
        } else {
            return new Student(updatedName, updateStudentId, updatedClassId, updatedEmail,
                    studentToEdit.getLabList());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private StudentId studentId;
        private ClassId classId;
        private Email email;
        private int result;
        private Lab lab;
        private ObservableList<Lab> labList;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setStudentId(toCopy.studentId);
            setClassId(toCopy.classId);
            setEmail(toCopy.email);
            setLab(toCopy.lab, toCopy.result);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, studentId, classId, email, lab);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setClassId(ClassId classId) {
            this.classId = classId;
        }

        public Optional<ClassId> getClassId() {
            return Optional.ofNullable(classId);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setLab(Lab lab, int result) {
            this.lab = lab;
            this.result = result;
        }

        public void setLabList(ObservableList<Lab> labList) {
            this.labList = labList;
        }

        public Optional<Lab> getLab() {
            return Optional.ofNullable(lab);
        }

        public Optional<Integer> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getStudentId().equals(e.getStudentId())
                    && getClassId().equals(e.getClassId())
                    && getEmail().equals(e.getEmail());
        }

    }
}

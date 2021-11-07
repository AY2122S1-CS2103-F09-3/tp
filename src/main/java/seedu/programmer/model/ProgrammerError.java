package seedu.programmer.model;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.UniqueStudentList;
import seedu.programmer.model.student.exceptions.DuplicateStudentException;

/**
 * Wraps all data at the programmer-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class ProgrammerError implements ReadOnlyProgrammerError {
    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();

    }

    public ProgrammerError() {}

    /**
     * Creates a ProgrammerError using the Persons in the {@code toBeCopied}
     */
    public ProgrammerError(ReadOnlyProgrammerError toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public boolean hasLab(Lab lab) {
        return students.hasLab(lab);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     * @throws DuplicateStudentException if {@code students} contains duplicate students.
     */
    public void setStudents(List<Student> students) throws DuplicateStudentException {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code ProgrammerError} with {@code newData}.
     */
    public void resetData(ReadOnlyProgrammerError newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in ProgrammerError.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in ProgrammerError.
     */
    public boolean hasSameStudentId(Student student) {
        requireNonNull(student);
        return students.containsSameStudentId(student);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in ProgrammerError.
     */
    public boolean hasSameStudentEmail(Student student) {
        requireNonNull(student);
        return students.containsSameEmail(student);
    }

    /**
     * Returns true if a student with the same identity as {@code editedStudent} exists in ProgrammerError.
     * excluding himself/herself.
     * Identity checking is done by checking if students have the same student ID or email.
     */
    public boolean hasOtherStudent(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        return students.containsOther(studentToEdit, editedStudent);
    }

    /**
     * Returns true if a student with the same identity as {@code editedStudent} exists in ProgrammerError.
     * excluding himself/herself.
     */
    public boolean hasOtherSameStudentId(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        return students.containsOtherSameStudentId(studentToEdit, editedStudent);
    }

    /**
     * Returns true if a student with the same identity as {@code editedStudent} exists in ProgrammerError.
     * excluding himself/herself.
     */
    public boolean hasOtherSameStudentEmail(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        return students.containsOtherSameEmail(studentToEdit, editedStudent);
    }


    /**
     * Adds a student to ProgrammerError.
     * The student must not already exist in ProgrammerError.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in ProgrammerError.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in ProgrammerError.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code ProgrammerError}.
     * {@code key} must exist in ProgrammerError.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Returns the selected information.
     */
    public ObservableList<DisplayableObject> getSelectedInformation() {
        return students.getSelectedInformation();
    }

    /**
     * Returns the selected student.
     */
    public Student getSelectedStudent() {
        return students.getSelectedStudent();
    }

    /**
     * Changes the selected student to the one specified by the input.
     */
    public void setSelectedStudent(Student target) {
        students.setSelectedStudent(target);
    }

    /**
     * Changes the selected labs.
     */
    public void setSelectedLabs(List<Lab> labs) {
        students.setSelectedLabs(labs);
    }

    /**
     * Clears the selected labs.
     */
    public void clearSelectedInformation() {
        students.clearSelectedInformation();
    }

    /**
     * Sets the lab tracker to the specified list of labs.
     */
    public void setLabsTracker(List<Lab> labs) {
        students.setLabsTracker(labs);
    }

    /**
     * Clears the lab tracker
     */
    public void clearLabsTracker() {
        students.clearLabsTracker();
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProgrammerError // instanceof handles nulls
                && students.equals(((ProgrammerError) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}

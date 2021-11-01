package seedu.programmer.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' ProgrammerError file path.
     */
    Path getProgrammerErrorFilePath();

    /**
     * Sets the user prefs' ProgrammerError file path.
     */
    void setProgrammerErrorFilePath(Path programmerErrorFilePath);

    /**
     * Replaces ProgrammerError data with the data in {@code programmerError}.
     */
    void setProgrammerError(ReadOnlyProgrammerError programmerError);

    /** Returns the ProgrammerError */
    ReadOnlyProgrammerError getProgrammerError();

    /**
     * Returns true if a student with the same identity as {@code student} exists in ProgrammerError.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in ProgrammerError.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the ProgrammerError.
     * */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the ProgrammerError.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the ProgrammerError.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns the selected information.*/
    ObservableList<DisplayableObject> getSelectedInformation();

    /** Returns the selected student.*/
    Student getSelectedStudent();

    /** Changes the selected student to the one specified by the input. */
    void setSelectedStudent(Student target);

    /** Changes the selected labs to the one specified by the input. */
    void setSelectedLabs(List<Lab> labs);

    /** Clears the selected labs. */
    void clearSelectedInformation();

    /** Sets the lab tracker to the specified list of labs. */
    void setLabsTracker(List<Lab> labs);

    /** Clears the labs tracker. */
    void clearLabsTracker();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}

package seedu.programmer.model;

import javafx.collections.ObservableList;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.UniqueStudentList;

/**
 * Unmodifiable view of a ProgrammerError
 */
public interface ReadOnlyProgrammerError {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns the student's Lab results.
     */
    ObservableList<Lab> showLabResultList(Student target);
}

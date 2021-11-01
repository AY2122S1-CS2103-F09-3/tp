package seedu.programmer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalStudents.ALICE;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.exceptions.DuplicateStudentException;
import seedu.programmer.testutil.StudentBuilder;

public class ProgrammerErrorTest {

    private final ProgrammerError programmerError = new ProgrammerError();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), programmerError.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> programmerError.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyProgrammerError_replacesData() {
        ProgrammerError newData = getTypicalProgrammerError();
        programmerError.resetData(newData);
        assertEquals(newData, programmerError);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two persons with the same StudentId but different Name
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        ProgrammerErrorStub newData = new ProgrammerErrorStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> programmerError.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> programmerError.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInProgrammerError_returnsFalse() {
        assertFalse(programmerError.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInProgrammerError_returnsTrue() {
        programmerError.addStudent(ALICE);
        assertTrue(programmerError.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameNameDifferentStudentId_returnsFalse() {
        programmerError.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertFalse(programmerError.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> programmerError.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyProgrammerError whose persons list can violate interface constraints.
     */
    private static class ProgrammerErrorStub implements ReadOnlyProgrammerError {
        private final ObservableList<Student> persons = FXCollections.observableArrayList();
        private final ObservableList<Lab> labs = FXCollections.observableArrayList();

        ProgrammerErrorStub(Collection<Student> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return persons;
        }
    }
}

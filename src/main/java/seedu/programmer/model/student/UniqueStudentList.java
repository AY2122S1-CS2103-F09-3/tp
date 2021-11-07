package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.model.student.comparator.SortByClass;
import seedu.programmer.model.student.comparator.SortByLabNumber;
import seedu.programmer.model.student.comparator.SortByStudentName;
import seedu.programmer.model.student.exceptions.DuplicateStudentException;
import seedu.programmer.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSamestudent(student)}. As such, adding and
 * updating of students uses student#isSamestudent(student) for equality so as to ensure that the student being added
 * or updated is unique in terms of identity in the UniqueStudentList. However, the removal of a student uses
 * student#equals(Object) so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private ObservableList<DisplayableObject> selectedInformation = FXCollections.observableArrayList();
    // Keep track of the existing labs
    private List<Lab> labsTracker = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);

    }

    /**
     * Returns true if the list contains an equivalent student with the same Email as the given argument.
     */
    public boolean containsSameEmail(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudentEmail);
    }


    /**
     * Returns true if the list contains an equivalent student with the same Student Id as the given argument.
     */
    public boolean containsSameStudentId(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudentId);
    }


    /**
     * Returns true if the list contains an equivalent student as the given argument，
     * excluding himself/herself.
     */
    public boolean containsOther(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        List<Student> studentListCopy = new ArrayList<>();
        for (Student student : internalList) {
            studentListCopy.add(student.copy());
        }
        studentListCopy.remove(studentToEdit);
        return studentListCopy.stream().anyMatch(editedStudent::isSameStudent);
    }

    public boolean hasLab(Lab lab) {
        return labsTracker.contains(lab);
    }


    /**
     * Returns true if the list contains an equivalent student as the given argument，
     * excluding himself/herself.
     */
    public boolean containsOtherSameEmail(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        List<Student> studentListCopy = new ArrayList<>();
        for (Student student : internalList) {
            studentListCopy.add(student.copy());
        }
        studentListCopy.remove(studentToEdit);
        return studentListCopy.stream().anyMatch(editedStudent::isSameStudentEmail);
    }


    /**
     * Returns true if the list contains an equivalent student as the given argument，
     * excluding himself/herself.
     */
    public boolean containsOtherSameStudentId(Student studentToEdit, Student editedStudent) {
        requireAllNonNull(studentToEdit, editedStudent);
        List<Student> studentListCopy = new ArrayList<>();
        for (Student student : internalList) {
            studentListCopy.add(student.copy());
        }
        studentListCopy.remove(studentToEdit);
        return studentListCopy.stream().anyMatch(editedStudent::isSameStudentId);
    }


    /**
     * Adds a student to the list and the list is sorted each time.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) throws DuplicateStudentException {
        requireNonNull(toAdd);
        if (toAdd.hasNoLabs()) {
            toAdd.setLabResultRecord(labsTracker);
        }

        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
        internalList.sort(new SortByClass().thenComparing(new SortByStudentName()));
    }

    /**
     * Get the student at the given index.
     * The student must not already exist in the list.
     */
    public Student get(int index) {
        return internalList.get(index);
    }

    /**
     * Get a list of all the students.
     */
    public ObservableList<Student> getList() {
        return internalList;
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        labsTracker.clear();
        labsTracker.addAll(editedStudent.getFreshLabList());

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        internalList.set(index, editedStudent);
        internalList.sort(new SortByClass().thenComparing(new SortByStudentName()));
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
        internalList.sort(new SortByClass().thenComparing(new SortByStudentName()));
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        internalList.sort(new SortByClass().thenComparing(new SortByStudentName()));
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!areStudentsUnique(students)) {
            throw new DuplicateStudentException();
        }
        if (!students.isEmpty()) {
            labsTracker.clear();
            labsTracker.addAll(students.get(0).getFreshLabList());
        }
        internalList.setAll(students);
        internalList.sort(new SortByClass().thenComparing(new SortByStudentName()));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean areStudentsUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the selected information.
     */
    public ObservableList<DisplayableObject> getSelectedInformation() {
        return this.selectedInformation;
    }

    /**
     * Returns the selected student.
     */
    public Student getSelectedStudent() {
        return (Student) this.selectedInformation.get(0);
    }

    /**
     * Changes the selected student to the one specified by the input.
     */
    public void setSelectedStudent(Student selectedStudent) {
        if (selectedInformation.isEmpty()) {
            this.selectedInformation.add(selectedStudent);
        } else {
            ObservableList<Lab> labList = selectedStudent.getLabList();
            labList.sort(new SortByLabNumber());
            this.selectedInformation.set(0, selectedStudent);
        }
    }

    /**
     * Changes the selected labs to the one specified by the input.
     */
    public void setSelectedLabs(List<Lab> labs) {
        if (!(selectedInformation.size() == 1)) {
            selectedInformation.remove(1, selectedInformation.size());
        }
        labs.sort(new SortByLabNumber());
        selectedInformation.addAll(labs);
    }

    /**
     * Clears the selected information.
     */
    public void clearSelectedInformation() {
        this.selectedInformation.clear();
    }

    /**
     * Sets the lab tracker to the specified list of labs.
     */
    public void setLabsTracker(List<Lab> labs) {
        this.labsTracker.clear();
        this.labsTracker.addAll(labs);
    }

    /**
     * Clears the labs tracker.
     */
    public void clearLabsTracker() {
        this.labsTracker.clear();
    }
}

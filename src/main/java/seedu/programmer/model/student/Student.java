package seedu.programmer.model.student;

import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.student.comparator.SortByLabNumber;


/**
 * Represents a student in the ProgrammerError.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    public static final String REQUIRE_POSITIVE_SCORE = "The student score should be a positive value.";

    public static final String EXCEEDED_TOTAL_SCORE = "The student score should be less than or equal to total score";

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final ClassId classId;
    private final Email email;
    private ObservableList<Lab> labList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentId studentId, ClassId classId, Email email) {
        requireAllNonNull(name, studentId, classId, email);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.email = email;
        this.labList = FXCollections.observableArrayList();
    }

    /**
     * Constructor for all fields including list of all the labs.
     */
    public Student(Name name, StudentId studentId, ClassId classId, Email email, ObservableList<Lab> list) {
        requireAllNonNull(name, studentId, classId, email);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.email = email;
        this.labList = list;
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.toString();
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public String getStudentIdValue() {
        return studentId.toString();
    }

    public ClassId getClassId() {
        return classId;
    }

    public String getClassIdValue() {
        return classId.toString();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailValue() {
        return email.toString();
    }

    public ObservableList<Lab> getLabList() {
        return labList;
    }

    /**
     * Returns a labList with all labs unmarked.
     * */
    public ObservableList<Lab> getFreshLabList() {
        ObservableList<Lab> freshCopy = FXCollections.observableArrayList();
        for (Lab lab : labList) {
            freshCopy.add(new Lab(lab.getLabNum(), lab.getTotalScore()));
        }
        return freshCopy;
    }

    public Lab getLab(int labNum) {
        return labList.stream().filter(x -> x.getLabNum() == labNum).findFirst().orElse(null);
    }

    //todo check comment out
    /**
     * Adds a lab to all the student records
     * */
    public Boolean addLab(Lab lab) {
        int index = this.labList.indexOf(lab);
        if (index == -1) {
            Lab newLab = lab.copy();
            this.labList.add(newLab);
            labList.sort(new SortByLabNumber());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a lab from all the student records
     * */
    public boolean deleteLab(Lab lab) {
        return this.labList.remove(lab);
    }

    /**
     * Updates a lab's score  for a student
     * */
    public void editLabScore(Lab lab , int score) throws CommandException {
        if (score < 0.0) {
            throw new CommandException(REQUIRE_POSITIVE_SCORE);
        }
        if (score > lab.getTotalScore()) {
            throw new CommandException(EXCEEDED_TOTAL_SCORE);
        }
        int index = this.labList.indexOf(lab);
        Lab current = this.labList.get(index);

        current.updateActualScore(score);
    }

    /**
     * Updates a lab result for a student
     * */
    public boolean editLabInfo(Lab lab, int newLabNum, int total) {
        Lab newLab = new Lab(newLabNum);
        int index2 = this.labList.indexOf(newLab);
        if (index2 == -1) {
            int index = this.labList.indexOf(lab);
            Lab current = this.labList.get(index);
            current.updateLabNum(newLabNum);
            current.updateTotal(total);
            labList.sort(new SortByLabNumber());
            return true;
        } else {
            return false;
        }
    }

    public void setLabResultRecord(List<Lab> labResultRecord) {
        if (labResultRecord == null) {
            labResultRecord = new ArrayList<>();
        }
        this.labList.addAll(labResultRecord);
    }

    public void setLabList(ObservableList<Lab> labList) {
        this.labList = labList;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getClassId().equals(getClassId())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getLabList().equals(getLabList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, classId, email);
    }

    @Override
    public String toString() {
        return getName()
                + "; Student ID: "
                + getStudentId()
                + "; Class ID: "
                + getClassId()
                + "; Email: "
                + getEmail();
    }

    /**
     * Create a new Student Object with the same fields.
     * @return a copy of the Student
     * */
    public Student copy() {
        Student studentCopy = new Student(name, studentId, classId, email);
        studentCopy.setLabResultRecord(labList);
        return studentCopy;
    }
}

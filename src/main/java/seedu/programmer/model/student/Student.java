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
public class Student implements DisplayableObject {

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
    public List<Lab> getFreshLabList() {
        List<Lab> freshCopy = new ArrayList<>();
        for (Lab lab : labList) {
            freshCopy.add(new Lab(lab.getLabNum(), lab.getLabTotal()));
        }
        freshCopy.sort(new SortByLabNumber());
        return freshCopy;
    }

    public Lab getLab(LabNum labNum) {
        return labList.stream().filter(x -> x.getLabNum().equals(labNum)).findFirst().orElse(null);
    }

    /**
     * Adds a lab to all the student records
     * @return true if lab is successfully added
     * */
    public boolean addLab(Lab lab) {
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
     * @returns ture if lab is successfully deleted
     * */
    public boolean deleteLab(Lab lab) {
        return this.labList.remove(lab);
    }

    /**
     * Updates a lab's score  for a student
     * */
    public void editLabScore(Lab lab , LabResult score) throws CommandException {
        Integer labTotalScore = lab.getLabTotal().getLabTotalScore();
        if (score.getLabResult() > labTotalScore) {
            throw new CommandException(warnExceedTotalScore(score.getLabResult(), labTotalScore));
        }
        int index = this.labList.indexOf(lab);
        Lab current = this.labList.get(index);

        current.updateActualScore(score);
    }

    /**
     * Updates a lab result for a student
     * @return true if lab info is successfully edited
     * */
    public boolean editLabInfo(Lab lab, LabNum newLabNum, LabTotal total) {
        Lab newLab = new Lab(newLabNum);
        int indexNewLab = this.labList.indexOf(newLab);
        if (indexNewLab == -1) {
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
            return;
        }

        for (Lab lab : labResultRecord) {
            labList.add(lab.copy());
        }
    }

    public void setLabList(ObservableList<Lab> labList) {
        this.labList = labList;
    }

    public boolean hasNoLabs() {
        return getLabList().isEmpty();
    }

    public int getNumLabs() {
        return labList.size();
    }

    /**
     * Returns true if both students have the same studentId or email.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        return isSameStudentId(otherStudent) || isSameStudentEmail(otherStudent);
    }

    /**
     * Returns true if both students have the same studentId.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudentEmail(Student otherStudent) {
        return otherStudent != null
                && otherStudent.getEmail().equals(getEmail());
    }


    /**
     * Returns true if both students have the same email.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudentId(Student otherStudent) {
        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    private String warnExceedTotalScore(int score, int totalScore) {
        return String.format("Error: New score %d exceeds total score of %d", score, totalScore);
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

    /**
     * Check if two students have identical personal information and lab results.
     */
    public boolean isIdenticalStudent(Student otherStudent) {
        boolean isIdentityFieldSame = otherStudent.getName().equals(getName())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getClassId().equals(getClassId())
                && otherStudent.getEmail().equals(getEmail());

        boolean isLabListSame = true;

        for (int i = 0; i < getLabList().size(); i++) {
            //check if the labNum, labResult and labTotal are all identical
            if (!getLabList().get(i).isIdenticalLab(otherStudent.getLabList().get(i))) {
                isLabListSame = false;
                break;
            }
        }

        return isIdentityFieldSame && isLabListSame;
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

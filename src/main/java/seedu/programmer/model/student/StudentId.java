package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's ID in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should be of the form AXXXXXXXY where X is a non-negative integer "
                + "and Y is the last character of your student ID."
                + "\nFor example: A0212345T";
  
    /*
     * A student ID must start and end with an alphabet, and has exactly 7 numbers between the two letters
     */
    private static final String VALIDATION_REGEX = "[A-Z][0-9]{7}[A-Z]";

    private final String studentId;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        this.studentId = studentId;
    }

    /**
     * Returns true if a given string is a valid StudentId.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && studentId.equals(((StudentId) other).studentId)); // state check
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }

}

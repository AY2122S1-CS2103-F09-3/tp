package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.commons.util.StringUtil;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.StudentId;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a positive integer.";
    public static final String MESSAGE_EMPTY_INDEX = "Please provide a positive integer as index.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name the input name
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param studentId the input studentId
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String formattedSid = studentId.trim().toUpperCase();
        if (!StudentId.isValidStudentId(formattedSid)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(formattedSid);
    }

    /**
     * Parses a {@code String classId} into an {@code ClassId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param classId the input classId
     * @throws ParseException if the given {@code classId} is invalid.
     */
    public static ClassId parseClassId(String classId) throws ParseException {
        requireNonNull(classId);
        String formattedCid = classId.trim().toUpperCase();
        if (!ClassId.isValidClassId(formattedCid)) {
            throw new ParseException(ClassId.MESSAGE_CONSTRAINTS);
        }
        return new ClassId(formattedCid);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email the input email
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String formattedEmail = email.trim().toLowerCase();
        if (!Email.isValidEmail(formattedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(formattedEmail);
    }

    /**
     * Parses the title of the lab assignment.
     *
     *  @param labNum the lab number
     * */
    public static int parseLabNum(String labNum) throws ParseException {
        requireNonNull(labNum);
        try {
            int value = Integer.parseInt(labNum);
            if (value <= 0 || value > 14) {
                throw new ParseException(Lab.MESSAGE_LAB_NUMBER_CONSTRAINT);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new ParseException(Lab.MESSAGE_LAB_NUMBER_CONSTRAINT);
        }
    }

    /**
     * Parses the result of the lab assignment.
     *
     * @param result the result of the lab assignment.
     * */
    public static Integer parseResult (String result) throws ParseException {
        try {
            String trimmedResult = result.trim();
            if (trimmedResult.isEmpty()) {
                throw new ParseException(Lab.MESSAGE_LAB_SCORE_CONSTRAINT);
            }
            Integer res = Integer.parseInt(trimmedResult);
            if (result == null) {
                return 0;
            } else if (res < 0) {
                throw new ParseException(Lab.MESSAGE_LAB_SCORE_CONSTRAINT);
            }
            return Integer.parseInt(result.trim());
        } catch (NumberFormatException e) {
            throw new ParseException(Lab.MESSAGE_LAB_SCORE_CONSTRAINT);
        }
    }

    /**
     * Parses the total score of the lab assignment
     *
     * @param total the total score of the lab assignment.
     * */
    public static Integer parseTotal(String total) throws ParseException {
        requireNonNull(total);
        try {
            String trimmedResult = total.trim();
            if (trimmedResult.isEmpty()) {
                throw new ParseException(Lab.MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT);
            }
            Integer res = Integer.parseInt(trimmedResult);
            if (total == null) {
                return 0;
            } else if (res <= 0 || res > 100) {
                throw new ParseException(Lab.MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT);
            }
            return Integer.parseInt(total.trim());
        } catch (NumberFormatException e) {
            throw new ParseException(Lab.MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT);
        }
    }
}

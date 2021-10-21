package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.commons.util.StringUtil;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.StudentId;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
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
        String trimmedSid = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedSid)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedSid);
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
        String trimmedCid = classId.trim();
        if (!ClassId.isValidClassId(trimmedCid)) {
            throw new ParseException(ClassId.MESSAGE_CONSTRAINTS);
        }
        return new ClassId(trimmedCid);
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
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    //todo: for test of show feature only

    /**
     * Parses the index of the student that the lab result is added to.
     *
     * @param index the index of the student.
     * */
    public static int parseNumber(String index) {
        if (index == null) {
            return 1;
        }
        return Integer.parseInt(index.trim());
    }

    /**
     * Parses the title of the lab assignment.
     *
     * @param title the title of the lab assignment.
     * */
    public static String parseTitle(String title) {
        if (title == null) {
            return " ";
        }
        return title.trim();
    }

    /**
     * Parses the result of the lab assignment.
     *
     * @param result the result of the lab assignment.
     * */
    public static Double parseResult(String result) {
        if (result == null) {
            return 0.0;
        }
        return Double.parseDouble(result.trim());
    }

    /**
     * Parses the total score of the lab assignment
     *
     * @param total the total score of the lab assignment.
     * */
    public static Double parseTotal(String total) {
        if (total == null) {
            return 0.0;
        }
        return Double.parseDouble(total.trim());
    }
}

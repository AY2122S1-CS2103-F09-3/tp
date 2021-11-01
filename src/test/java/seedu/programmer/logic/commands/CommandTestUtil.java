package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NEW_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;
import seedu.programmer.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_ID_AMY = "A0232134H";
    public static final String VALID_STUDENT_ID_BOB = "A0232132B";
    public static final String VALID_CLASS_ID_AMY = "B01";
    public static final String VALID_CLASS_ID_BOB = "B02";
    public static final String VALID_EMAIL_AMY = "e0313455@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "e0532115@u.nus.edu";

    public static final Integer VALID_LAB_NO = 1;
    public static final Integer VALID_ACTUAL_SCORE = 15;
    public static final Integer VALID_TOTAL_SCORE = 20;
    public static final Integer VALID_LAB_NO2 = 1;
    public static final Integer VALID_ACTUAL_SCORE2 = 15;
    public static final Integer VALID_TOTAL_SCORE2 = 20;
    public static final String NEW_LAB_NUM = " " + PREFIX_LAB_NEW_LAB_NUM + VALID_LAB_NO2;
    public static final String LAB_NUM = " " + PREFIX_LAB_NUM + VALID_LAB_NO;
    public static final String LAB_TOTAL = " " + PREFIX_LAB_TOTAL + VALID_ACTUAL_SCORE;
    public static final String LAB_RESULT = " " + PREFIX_LAB_RESULT + VALID_TOTAL_SCORE;
    public static final String LAB_NUM2 = " " + PREFIX_LAB_NUM + VALID_LAB_NO2;
    public static final String LAB_TOTAL2 = " " + PREFIX_LAB_TOTAL + VALID_ACTUAL_SCORE2;
    public static final String LAB_RESULT2 = " " + PREFIX_LAB_RESULT + VALID_TOTAL_SCORE2;
    public static final String INVALID_NEW_LAB_NUM = " " + PREFIX_LAB_NUM + (-2);
    public static final String INVALID_LAB_NUM = " " + PREFIX_LAB_NUM + (-1);
    public static final String INVALID_LAB_TOTAL = " " + PREFIX_LAB_TOTAL + -10;
    public static final String INVALID_LAB_RESULT = " " + PREFIX_LAB_RESULT + -20;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String CLASS_ID_DESC_AMY = " " + PREFIX_CLASS_ID + VALID_CLASS_ID_AMY;
    public static final String CLASS_ID_DESC_BOB = " " + PREFIX_CLASS_ID + VALID_CLASS_ID_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    //todo
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID + "911a"; // 'a' not allowed in phones
    public static final String INVALID_CLASS_ID_DESC = " " + PREFIX_CLASS_ID + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL; // empty string not allowed for programmeres

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY).withClassId(VALID_CLASS_ID_AMY).withemail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB).withClassId(VALID_CLASS_ID_BOB).withemail(VALID_EMAIL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the ProgrammerError, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ProgrammerError expectedProgrammerError = new ProgrammerError(actualModel.getProgrammerError());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedProgrammerError, actualModel.getProgrammerError());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s ProgrammerError.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());

        QueryStudentDescriptor descriptor = new QueryStudentDescriptor(
                student.getNameValue(), student.getStudentIdValue(), null, null);

        model.updateFilteredStudentList(new StudentDetailContainsQueryPredicate(descriptor));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}

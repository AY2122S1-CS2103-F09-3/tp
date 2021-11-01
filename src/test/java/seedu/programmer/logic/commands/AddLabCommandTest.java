package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_FIRST_LAB;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;
import seedu.programmer.testutil.LabBuilder;

public class AddLabCommandTest {
    private static Lab validLab;
    private static Lab sampleLabA;
    private static AddLabCommand sampleCommandA;
    private static AddLabCommand sampleCommandB;

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validLab = new LabBuilder().build();
        sampleLabA = new LabBuilder().withLabNum(10).withTotal(20).build();
        Lab sampleLabB = new LabBuilder().withLabNum(11).withTotal(30).build();
        sampleCommandA = new AddLabCommand(sampleLabA);
        sampleCommandB = new AddLabCommand(sampleLabB);
    }

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLabCommand(null));
    }

    @Test
    public void execute_labTitleAlreadyExists_throwsCommandException() throws Exception {
        Lab labToAdd = getTypicalLabList().get(NUMBER_FIRST_LAB);
        AddLabCommand addLabCommand = new AddLabCommand(labToAdd);

        String expectedMessage = String.format(AddLabCommand.MESSAGE_LAB_ALREADY_EXISTS, labToAdd);

        assertCommandFailure(addLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_labAdded_success() {
        Lab labToAdd = sampleLabA;
        AddLabCommand addLabCommand = sampleCommandA;

        String expectedMessage = String.format(AddLabCommand.MESSAGE_ADD_LAB_SUCCESS, labToAdd);

        Model expectedModel = new ModelManager(new ProgrammerError(model.getProgrammerError()), new UserPrefs());
        //expectedModel.addLab(labToAdd);

        assertCommandSuccess(addLabCommand, model, expectedMessage, expectedModel);
    }
}


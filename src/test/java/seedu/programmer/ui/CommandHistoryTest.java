package seedu.programmer.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.ui.CommandHistory.DEFAULT_COMMAND;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    public void add_sameInputs_equal() {
        CommandHistory commandHistoryOne = new CommandHistory();
        commandHistoryOne.add("test input one");
        commandHistoryOne.add("test input two");

        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test input one");

        assertNotEquals(commandHistoryOne, commandHistoryTwo);

        commandHistoryTwo.add("test input two");
        assertEquals(commandHistoryOne, commandHistoryTwo);

        commandHistoryTwo.add("test input three");
        assertNotEquals(commandHistoryOne, commandHistoryTwo);
    }

    @Test
    public void add_differentInputs_notEqual() {
        CommandHistory commandHistoryOne = new CommandHistory();
        commandHistoryOne.add("test input one");
        commandHistoryOne.add("test input two");

        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test input two");
        commandHistoryTwo.add("test input one");

        assertNotEquals(commandHistoryOne, commandHistoryTwo);
    }

    @Test
    public void getPrevCommand_emptyHistory_returnDefaultCommand() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals(commandHistory.getPrevCommand(), DEFAULT_COMMAND);
    }

    @Test
    public void getPrevCommand_validHistory_success() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("one");
        commandHistory.add("two");

        // Retrieve latest command
        assertEquals(commandHistory.getPrevCommand(), "two");
        // Retrieve next latest command
        assertEquals(commandHistory.getPrevCommand(), "one");
        // Oldest command is returned when there is no older history
        assertEquals(commandHistory.getPrevCommand(), "one");
    }

    @Test
    public void getNextCommand_emptyHistory_returnDefaultCommand() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals(commandHistory.getNextCommand(), DEFAULT_COMMAND);
    }

    @Test
    public void getNextCommand_validHistory_success() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("one");
        commandHistory.add("two");
        commandHistory.add("three");

        // getPrevCommand has not been executed --> expect the default empty command
        assertEquals(commandHistory.getNextCommand(), "");

        // Execute getPrevCommand to the oldest command
        commandHistory.getPrevCommand();
        commandHistory.getPrevCommand();
        commandHistory.getPrevCommand();

        // Retrieve the next command
        assertEquals(commandHistory.getNextCommand(), "two");
        // Retrieve the next command
        assertEquals(commandHistory.getNextCommand(), "three");
    }

    @Test
    public void equals() {
        CommandHistory commandHistoryOne = new CommandHistory();
        commandHistoryOne.add("test");
        CommandHistory commandHistoryOneCopy = new CommandHistory();
        commandHistoryOneCopy.add("test");
        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test");
        commandHistoryTwo.add("test2");
        CommandHistory commandHistoryTwoCopy = new CommandHistory();
        commandHistoryTwoCopy.add("test");
        commandHistoryTwoCopy.add("test2");
        commandHistoryTwoCopy.getPrevCommand();

        // same values -> returns true
        assertEquals(commandHistoryOne, commandHistoryOneCopy);

        // same object -> returns true
        assertEquals(commandHistoryOne, commandHistoryOne);

        // null -> returns false
        assertNotEquals(null, commandHistoryOne);

        // different type -> returns false
        assertNotEquals(5, commandHistoryOne);

        // different commandHistory -> returns false
        assertNotEquals(commandHistoryOne, commandHistoryTwo);

        // different counter -> returns false
        assertNotEquals(commandHistoryTwo, commandHistoryTwoCopy);
    }
}

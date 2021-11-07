package seedu.programmer.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.programmer.commons.core.LogsCenter;

public class CommandHistory {
    private final Logger logger = LogsCenter.getLogger(getClass());
    private List<String> commandHistory;
    private int currCommandIndex;

    /**
     * Constructor to initialize a new {@code CommandHistory} object.
     */
    public CommandHistory() {
        commandHistory = new ArrayList<>();
    }

    /**
     * Adds the {@code command} to the {@code commandHistory}.
     * @param command The string to be added to the history of commands.
     */
    public void add(String command) {
        commandHistory.add(command);
        currCommandIndex = commandHistory.size() - 1;
    }

    /**
     * Returns the previous command entered according to the {@code counter} pointer.
     *
     * @return The string of the previous command.
     */
    public String getPrevCommand() {
        // We should not call getPrevCommand() if the counter is already at the oldest command.
        assert !isAtFirstIndex();
        currCommandIndex--;
        String result = commandHistory.get(currCommandIndex);
        logger.info("Previous Command retrieved: " + result);
        return result;
    }

    /**
     * Returns the next command entered according to the {@code counter} pointer.
     *
     * @return The string of the next most recent command.
     */
    public String getNextCommand() {
        // We should not call getNextCommand() if the counter is already at the latest command.
        assert !isAtLastIndex();
        currCommandIndex++;
        String result = commandHistory.get(currCommandIndex);
        logger.info("Next Command retrieved: " + result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandHistory)) {
            return false;
        }

        // state check
        CommandHistory e = (CommandHistory) other;
        return commandHistory.equals(e.commandHistory)
                && currCommandIndex == e.currCommandIndex;
    }

    /**
     * Checks if the {@code commandHistory} is empty.
     * {@code commandHistory} is empty if no commands has been executed.
     * @return boolean true if {@code commandHistory} is empty and false otherwise.
     */
    public boolean isEmpty() {
        return commandHistory.size() == 0;
    }

    /**
     * Checks if the {@code currCommandIndex} is pointing at the most recently executed command.
     * @return boolean true if the {@code currCommandIndex} is pointing at the most recently executed command and
     * false otherwise.
     */
    public boolean isAtLastIndex() {
        return currCommandIndex == commandHistory.size() - 1;
    }

    /**
     * Checks if the {@code currCommandIndex} is pointing at the least recently executed command.
     * @return boolean true if the {@code currCommandIndex} is pointing at the least recently executed command and
     * false otherwise.
     */
    public boolean isAtFirstIndex() {
        return currCommandIndex == 0;
    }

    /**
     * Returns the command where {@code currCommandIndex} is pointing at.
     * @return String of the command being pointed at the current moment.
     */
    public String getCurrentCommand() {
        return isEmpty() ? "" : commandHistory.get(currCommandIndex);
    }
}

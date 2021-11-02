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
     * Returns the next most recently entered command according to the {@code counter} pointer.
     * Returns the least recent command if the {@code counter} is already pointer at the oldest command.
     * @return The string of the next most recent entered command.
     */
    public String getPrevCommand() {
        // We should not call getPrevCommand() if the counter is already at the oldest command.
        assert !isCounterAtFirst();
        String result = commandHistory.get(currCommandIndex);
        currCommandIndex--;
        logger.info("Previous Command retrieved: " + result);
        return result;
    }

    /**
     * Returns the next least recent entered command according to the {@code counter} pointer.
     * Returns the most recent command if the {@code counter} is already pointer at the latest command.
     * @return The string of the next least recent entered command.
     */
    public String getNextCommand() {
        // We should not call getNextCommand() if the counter is already at the latest command.
        assert !isCounterAtLast();
        String result = commandHistory.get(currCommandIndex);
        currCommandIndex++;
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

    public boolean isEmpty() {
        return commandHistory.size() == 0;
    }

    public boolean isCounterAtLast() {
        return currCommandIndex == commandHistory.size() - 1;
    }

    public boolean isCounterAtFirst() {
        return currCommandIndex == 0;
    }

    public String getCurrentCommand() {
        return isEmpty() ? "" : commandHistory.get(currCommandIndex);
    }
}

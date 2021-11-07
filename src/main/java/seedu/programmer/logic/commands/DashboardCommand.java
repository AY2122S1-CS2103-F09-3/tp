package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Dashboard command for user to see statistics.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays dashboard of data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_DASHBOARD_MESSAGE = "Opened dashboard window.";

    /**
     * Returns a DashboardCommandResult with a message to indicate that the command was executed.
     *
     * @param model {@code Model} which the command should operate on
     * @return a DashboardCommandResult with a message
     */
    @Override
    public DashboardCommandResult execute(Model model) {
        return new DashboardCommandResult(SHOWING_DASHBOARD_MESSAGE);
    }
}

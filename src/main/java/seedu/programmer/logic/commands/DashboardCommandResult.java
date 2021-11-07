package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI.
 */
public class DashboardCommandResult extends CommandResult {

    /**
     * Creates a DashboardCommandResult with a message.
     *
     * @param feedbackToUser message to be displayed to the user
     */
    public DashboardCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}

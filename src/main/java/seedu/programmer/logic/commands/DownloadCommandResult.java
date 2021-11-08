package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI.
 */
public class DownloadCommandResult extends CommandResult {

    /**
     * Creates a DownloadCommandResult.
     *
     * @param feedbackToUser Message to show user.
     * @param model Current model.
     */
    public DownloadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}

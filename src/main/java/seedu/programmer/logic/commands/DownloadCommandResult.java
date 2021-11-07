package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI.
 */
public class DownloadCommandResult extends CommandResult {

    /**
     * Creates a DownloadCommandResult.
     *
     * @param feedbackToUser message to show user
     * @param model current model
     */
    public DownloadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}

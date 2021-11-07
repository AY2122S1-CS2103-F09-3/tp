package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI.
 */
public class UploadCommandResult extends CommandResult {

    /**
     * Creates an UploadCommandResult.
     *
     * @param feedbackToUser message to show user
     * @param model current model
     */
    public UploadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}

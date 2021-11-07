package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class UploadCommand extends Command {

    public static final String COMMAND_WORD = "upload";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads student data as CSV.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_UPLOAD_MESSAGE = "Upload command executed.";

    /**
     * Returns an UploadCommandResult with a message to indicate that the command was executed.
     *
     * @param model {@code Model} which the command should operate on
     * @return UploadCommandResult with a message
     */
    @Override
    public UploadCommandResult execute(Model model) {
        return new UploadCommandResult(SHOWING_UPLOAD_MESSAGE);
    }
}

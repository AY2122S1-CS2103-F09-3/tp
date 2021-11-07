package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Downloads student data as CSV.\n"
            + "Example: " + COMMAND_WORD;
    public static final String SHOWING_DOWNLOAD_MESSAGE = "Download command executed.";

    /**
     * Returns a DownloadCommandResult with a message to indicate that the command was executed.
     *
     * @param model {@code Model} which the command should operate on
     * @return DownloadCommandResult with a message
     */
    @Override
    public DownloadCommandResult execute(Model model) {
        return new DownloadCommandResult(SHOWING_DOWNLOAD_MESSAGE);
    }
}

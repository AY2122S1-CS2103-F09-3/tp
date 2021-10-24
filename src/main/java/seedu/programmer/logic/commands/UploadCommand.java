package seedu.programmer.logic.commands;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import seedu.programmer.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class UploadCommand extends Command {

    public static final String COMMAND_WORD = "upload";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads student data as CSV.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new UploadCommandResult("Upload command executed", model);
    }
}

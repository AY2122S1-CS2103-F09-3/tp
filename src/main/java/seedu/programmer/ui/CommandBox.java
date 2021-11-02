package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final CommandExecutor commandExecutor;
    private final CommandHistory commandHistory = new CommandHistory();

    private boolean lastWasSuccess = false;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandHistory.add(commandText);
            commandExecutor.execute(commandText);
            commandTextField.setText("");
            lastWasSuccess = true;
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
            lastWasSuccess = false;
        }
    }

    /**
     * Handles the Up and Down arrow keys pressed event.
     * @param event The input event trigger this method to be invoked.
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        boolean upPressed = event.getCode() == KeyCode.UP;
        boolean downPressed = event.getCode() == KeyCode.DOWN;
        boolean isNotUpOrDown = !upPressed && !downPressed;
        boolean noCommandHistoryPresent = commandHistory.isEmpty();
        boolean downAndAtLastCommand = downPressed && commandHistory.isCounterAtLast();
        boolean upAndAtFirstCommand = upPressed && commandHistory.isCounterAtFirst();

        // Do nothing if neither up nor down pressed, or no command history
        if (isNotUpOrDown || noCommandHistoryPresent) {
            return;
        }

        if (upPressed && lastWasSuccess) {
            commandTextField.setText(commandHistory.getCurrentCommand());
            lastWasSuccess = false;
            return;
        }

        if (downAndAtLastCommand || upAndAtFirstCommand) {
            logger.info("We are already at the oldest or newest command -> show current command");
            commandTextField.setText(commandHistory.getCurrentCommand());
        } else if (upPressed) {
            commandTextField.setText(commandHistory.getPrevCommand());
        } else {
            commandTextField.setText(commandHistory.getNextCommand());
        }

        commandTextField.end(); // Move cursor to end of text field
        event.consume(); // Consume Event
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.programmer.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}

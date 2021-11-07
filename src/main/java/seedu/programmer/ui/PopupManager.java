package seedu.programmer.ui;

import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Popup manager for creating, configuring and displaying popups on a stage.
 */
public class PopupManager {

    private static final Double PADDING_FACTOR = 0.1;

    private final Popup popup;
    private final Stage primaryStage;

    /**
     * Creates a popup manager for the given primaryStage.
     *
     * @param primaryStage stage to display popups on
     */
    public PopupManager(Stage primaryStage) {
        this.popup = new Popup();
        this.primaryStage = primaryStage;
    }

    /**
     * Displays a popup message at the top-center with respect to the primaryStage.
     *
     * @param message to be displayed in the popup object on the primaryStage
     */
    void displayPopup(String message) {
        // We should not display an empty popup
        assert (message != null);
        configurePopup(message);
        popup.show(primaryStage);
    }

    /**
     * Creates a Popup object with a message.
     *
     * @param message The text to display to the user
     */
    private void configurePopup(String message) {
        popup.setAutoFix(false); // Prevent unwanted corrections in edge cases
        popup.setHideOnEscape(true);
        Label label = createLabelForPopup(message);
        popup.getContent().clear();
        popup.getContent().add(label);

        // Add some left padding according to primaryStage's width
        popup.setX(primaryStage.getX() + primaryStage.getWidth() * PADDING_FACTOR / 2);

        // Set Y coordinate scaled according to primaryStage's height
        popup.setY(primaryStage.getY() + primaryStage.getHeight() * PADDING_FACTOR);
    }

    /**
     * Creates a Label object with a message and styling.
     *
     * @param message to be displayed in the label
     * @return a Label object with the message and styling
     */
    private Label createLabelForPopup(String message) {
        Label label = new Label(message);
        double customMaxWidth = primaryStage.getWidth() * (1 - PADDING_FACTOR);
        label.setWrapText(true);
        label.setMaxWidth(customMaxWidth);
        label.getStyleClass().add("popup-label");

        // Hide popup when the user clicks on it
        label.setOnMouseReleased(e -> popup.hide());
        return label;
    }
}

package seedu.programmer.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.programmer.model.student.Lab;

/**
 * An UI component that displays information of the lab result.
 */
public class LabResultCard extends UiPart<Region> {

    private static final String FXML = "LabResultCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Lab result;

    @FXML
    private HBox cardPane;
    @FXML
    private Label labTitle;
    @FXML
    private Label studentScore;
    @FXML
    private Label totalScore;


    /**
     * Creates a {@code labResultCode} with the given {@code labResult} and index to display.
     */
    public LabResultCard(Lab result, int displayedIndex) {
        super(FXML);
        this.result = result;
        labTitle.setText(result.toString());
        totalScore.setText("Total Score: " + result.getTotalScore());
        if (result.getActualScore() == 0) {
            studentScore.setText("Actual Score: -");
        } else {
            studentScore.setText("Actual Score: " + result.getActualScore());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LabResultCard)) {
            return false;
        }

        // state check
        LabResultCard card = (LabResultCard) other;
        return result.equals(card.result);
    }
}

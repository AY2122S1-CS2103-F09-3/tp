package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Panel containing the list of lab results
 */
public class LabResultListPanel extends UiPart<Region> {
    private static final String FXML = "LabResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LabResultListPanel.class);

    @FXML
    private ListView<DisplayableObject> labResultListView;

    /**
     * Creates a {@code LabelResultListPanel} with the given {@code ObservableList}.
     */
    public LabResultListPanel(ObservableList<DisplayableObject> displayableObjects) {
        super(FXML);
        labResultListView.setItems(displayableObjects);
        labResultListView.setCellFactory(listView -> new LabListListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code labResult} using a {@code LabResultCard}.
     */
    static class LabListListViewCell extends ListCell<DisplayableObject> {
        @Override
        protected void updateItem(DisplayableObject item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else if (item instanceof Student) {
                Student student = (Student) item;
                setGraphic(new StudentCard(student).getRoot());
            } else {
                Lab labResult = (Lab) item;
                setGraphic(new LabResultCard(labResult, getIndex() + 1).getRoot());
            }
        }
    }
}

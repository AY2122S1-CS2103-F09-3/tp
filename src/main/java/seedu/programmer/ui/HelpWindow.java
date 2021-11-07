package seedu.programmer.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Controller for a help window
 */
public class HelpWindow extends PopupWindow {

    public static final String USER_GUIDE_URL = "https://ay2122s1-cs2103-f09-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Full user guide: " + USER_GUIDE_URL;
    private static final String DESCRIPTION = "Here's a list of ProgrammerError's features:";

    private static final String FXML = "HelpWindow.fxml";
    private static final int FEATURE_COL_WIDTH = 1200;
    private static final Double FRACTION_OF_WINDOW = 0.95;
    private static final double HELP_WINDOW_WIDTH = Screen.getPrimary().getBounds().getWidth() * FRACTION_OF_WINDOW;

    private ObservableList<FeatureTableItem> featureTableItems;

    @FXML
    private Button copyButton;

    @FXML
    private Label description;

    @FXML
    private Label helpMessage;

    @FXML
    private TextArea featureList;

    @FXML
    private TableView<FeatureTableItem> featureTable;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        description.setText(DESCRIPTION);
        helpMessage.setText(HELP_MESSAGE);
        initializeFeatureList();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
        getRoot().setWidth(HELP_WINDOW_WIDTH);
    }

    private void initializeFeatureList() {
        featureTableItems = FXCollections.observableArrayList();

        initializeGeneralFeatures();
        initializeDataFeatures();
        initializeStudentFeatures();
        initializeLabFeatures();

        initializeTableColumns();
    }

    private void initializeGeneralFeatures() {
        FeatureTableItem help = new FeatureTableItem("Help",
                "help / Click Help button / Press F2",
                "Opens this help window");
        FeatureTableItem dashboard = new FeatureTableItem("Dashboard",
                "dashboard / Click Dashboard button / Press F5",
                "Shows a dashboard of ProgrammerError data");
        FeatureTableItem exit = new FeatureTableItem("Exit",
                "exit / Click Exit button / Press F1",
                "Closes ProgrammerError");
        featureTableItems.addAll(help, dashboard, exit);
    }

    private void initializeDataFeatures() {
        FeatureTableItem fill = new FeatureTableItem("Fill",
                "fill",
                "Fills ProgrammerError with sample data, only when the database is empty");
        FeatureTableItem purge = new FeatureTableItem("Purge",
                "purge",
                "Deletes all data");
        FeatureTableItem download = new FeatureTableItem("Download",
                "download / Click Download Button / Press F3",
                "Downloads the database as a csv file");
        FeatureTableItem upload = new FeatureTableItem("Upload",
                "upload / Press Upload button / Press F4",
                "Uploads a CSV file containing students' details to ProgrammerError's database");

        featureTableItems.addAll(fill, purge, download, upload);
    }

    private void initializeStudentFeatures() {
        FeatureTableItem add = new FeatureTableItem("Add",
                "add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID> -email <EMAIL>",
                "Creates a record of a new student");
        FeatureTableItem edit = new FeatureTableItem("Edit",
                "edit <INDEX_IN_LIST> -n <NAME> / -sid <STUDENT_ID> / -cid <CLASS_ID> / -email <EMAIL>/ "
                        + "-ln <LAB_NUMBER> -s <LAB_SCORE>",
                "Edits a student's specified field to the new value. Multiple fields can be edited at the same time");
        FeatureTableItem delete = new FeatureTableItem("Delete",
                "delete <INDEX_IN_LIST>",
                "Deletes the student record at the index");
        FeatureTableItem filter = new FeatureTableItem("Filter",
                "filter -n <NAME> / -sid <STUDENT_ID> / -cid <CLASS_ID> / -email <EMAIL>",
                "Filters the list of students with the specified parameters. At least one parameter must be provided.");
        FeatureTableItem show = new FeatureTableItem("Show",
                "show <INDEX_IN_LIST>",
                "Shows the lab results of the chosen student");
        FeatureTableItem list = new FeatureTableItem("List",
                "list",
                "Displays all students records in the database");

        featureTableItems.addAll(add, edit, delete, filter, show, list);
    }

    private void initializeLabFeatures() {
        FeatureTableItem addLab = new FeatureTableItem("AddLab",
                "addlab -ln <LAB_NUMBER> -ts <TOTAL_SCORE>",
                "Creates a lab record for all students in the database");
        FeatureTableItem delLab = new FeatureTableItem("DeleteLab",
                "dellab -ln <LAB_NUMBER>",
                "Removes the lab with the corresponding lab number from ProgrammerError");
        FeatureTableItem editLab = new FeatureTableItem("EditLab",
                "editlab -ln <LAB_NUMBER> -nln <NEW_LAB_NUMBER> / -ts <TOTAL_SCORE>",
                "Edits an existing lab's lab number and/or total score");

        featureTableItems.addAll(addLab, editLab, delLab);
    }

    private void initializeTableColumns() {
        TableColumn<FeatureTableItem, String> featureColumn = new TableColumn<>("Feature");
        featureColumn.setCellValueFactory(new PropertyValueFactory<>("feature"));
        featureColumn.setMaxWidth(FEATURE_COL_WIDTH);
        TableColumn<FeatureTableItem, String> commandColumn = new TableColumn<>("Command Syntax");
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        TableColumn<FeatureTableItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        featureTable.getColumns().add(featureColumn);
        featureTable.getColumns().add(commandColumn);
        featureTable.getColumns().add(descriptionColumn);
        featureTable.setItems(featureTableItems);
        featureTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

    protected static class FeatureTableItem {
        private final SimpleStringProperty feature;
        private final SimpleStringProperty command;
        private final SimpleStringProperty description;


        public FeatureTableItem(String feature, String command, String description) {
            this.feature = new SimpleStringProperty(feature);
            this.command = new SimpleStringProperty(command);
            this.description = new SimpleStringProperty(description);
        }

        public String getFeature() {
            return feature.get();
        }

        public String getCommand() {
            return command.get();
        }

        public String getDescription() {
            return description.get();
        }
    }
}

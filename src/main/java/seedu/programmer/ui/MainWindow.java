package seedu.programmer.ui;

import static seedu.programmer.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opencsv.CSVReader;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.Logic;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.DownloadCommandResult;
import seedu.programmer.logic.commands.ExitCommandResult;
import seedu.programmer.logic.commands.HelpCommandResult;
import seedu.programmer.logic.commands.ShowCommandResult;
import seedu.programmer.logic.commands.UploadCommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StudentCard studentParticular;

    @FXML
    private Scene primaryScene;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button downloadButton;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane labResultListPanelPlaceholder;

    @FXML
    private StackPane studentParticularPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(KeyCombination.valueOf("F1"), this::handleExit);
        setAccelerator(KeyCombination.valueOf("F2"), this::handleHelp);
        setAccelerator(KeyCombination.valueOf("F3"), this::handleDownload);
        setAccelerator(KeyCombination.valueOf("F4"), this::handleUpload);
    }

    /**
     * Sets the accelerator of a button.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(KeyCombination keyCombination, Runnable runnable) {
        primaryScene.getAccelerators().put(keyCombination, runnable);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getProgrammerErrorFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() == null) {
            return;
        }

        primaryStage.setX(guiSettings.getWindowCoordinates().getX());
        primaryStage.setY(guiSettings.getWindowCoordinates().getY());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (helpWindow.isShowing()) {
            helpWindow.focus();
            return;
        }
        helpWindow.show();
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application window.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Display the selected student's lab results.
     */
    @FXML
    public void handleShowResult(Student target) {
        if (studentParticularPlaceholder.getChildren().isEmpty()) {
            studentParticular = new StudentCard(target);
            studentParticularPlaceholder.getChildren().add(studentParticular.getRoot());
        } else {
            studentParticular.updateStudentInformation(target);
            studentParticularPlaceholder.getChildren().set(0, studentParticular.getRoot());
        }
        LabResultListPanel labResultListPanel = new LabResultListPanel(logic.getLabResultList(target));
        labResultListPanelPlaceholder.getChildren().add(labResultListPanel.getRoot());
    }

    /**
     * Uploads CSV data into ProgrammerError's model storage.
     */
    @FXML
    private void handleUpload() {
        File chosenFile = promptUserForCsvFile();
        List<Student> stuList;
        try {
            stuList = getStudentsFromCsv(chosenFile);
        } catch (IllegalArgumentException | IOException e) {
            displayPopup("Your CSV seems to be invalid. It should only have studentId, classId, name and email!");
            return;
        }

        if (stuList == null) {
            displayPopup("Incorrect number of columns!");
            return;
        }

        ProgrammerError newPE = new ProgrammerError();
        newPE.setStudents(stuList);
        logic.updateProgrammerError(newPE);
        logic.updateFilteredStudents(PREDICATE_SHOW_ALL_STUDENTS);
        try {
            logic.getStorage().saveProgrammerError(newPE);
        } catch (IOException e) {
            displayPopup("An unexpected error has occurred, please try again!");
            return;
        }
        logger.info("Uploaded CSV data successfully!");
    }

    private File promptUserForCsvFile() {
        FileChooser fileChooser = new FileChooser();
        configureCsvFileChooser(fileChooser);
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * Get a List of Students from CSV file of student data.
     *
     * @param chosenFile file chosen by user
     * @return List of Students in the CSV file
     * @throws IllegalArgumentException if CSV contains invalid input
     * @throws IOException if error reading the file
     */
    private List<Student> getStudentsFromCsv(File chosenFile) throws IllegalArgumentException, IOException {
        List<Student> stuList = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(chosenFile));
        String[] headers = reader.readNext();
        int numberOfFields = 4;
        if (headers.length != numberOfFields) {
            return null;
        }

        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            addStudentFromCsvLine(stuList, nextLine);
        }

        return stuList;
    }

    /**
     * Adds a Student to a List of Students.
     *
     * @param stuList Student list to add to
     * @param nextLine line in a CSV file of student data
     */
    private void addStudentFromCsvLine(List<Student> stuList, String[] nextLine) {
        StudentId sid = new StudentId(nextLine[0]);
        ClassId cid = new ClassId(nextLine[1]);
        Name name = new Name(nextLine[2]);
        Email email = new Email(nextLine[3]);
        Student s = new Student(name, sid, cid, email);
        stuList.add(s);
    }

    /**
     * Configure file chooser to accept only CSV files
     *
     * @param fileChooser FileChooser object
     */
    private static void configureCsvFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Select CSV file");
        ExtensionFilter csvFilter = new ExtensionFilter("All CSVs", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);
    }

    /**
     * Downloads the JSON data as a CSV file to the user's chosen directory.
     */
    @FXML
    private void handleDownload() {
        JSONArray jsonData = getJsonData();
        assert (jsonData != null);

        if (jsonData.length() == 0) {
            displayPopup("No data to download!");
            return;
        }

        File destinationFile = promptUserForDestination();
        if (destinationFile != null) {
            writeJsonToCsv(jsonData, destinationFile);
            displayPopup("Your data has been downloaded to " + destinationFile + "!");
            logger.info("Data successfully downloaded as CSV.");
        }
    }

    /**
     * Display the pop-up message on the UI.
     *
     * @param message message to show user
     */
    private void displayPopup(String message) {
        // We should not need to display an empty popup
        assert (message != null);
        Popup popup = createPopup(message);
        showPopupMessage(popup);
    }

    /**
     * Displays a popup message at the top-center with respect to the primaryStage.
     *
     * @param popup Popup object to be displayed on the primaryStage
     */
    private void showPopupMessage(Popup popup) {
        // Add some left padding according to primaryStage's width
        popup.setX(primaryStage.getX() + primaryStage.getWidth() * 0.04);

        // Set Y coordinate scaled according to primaryStage's height
        popup.setY(primaryStage.getY() + primaryStage.getHeight() * 0.1);
        popup.show(primaryStage);
    }

    /**
     * Creates a Popup object with a message.
     *
     * @param message The text to display to the user
     * @return a Popup object
     */
    private Popup createPopup(String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);

        Label label = new Label(message);
        label.getStylesheets().add("view/Styles.css");
        label.getStyleClass().add("popup");

        // Hide popup when the user clicks on it
        label.setOnMouseReleased(e -> popup.hide());

        popup.getContent().add(label);
        return popup;
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param jsonData JSONArray of data
     * @param destinationFile File object to write to
     */
    private void writeJsonToCsv(JSONArray jsonData, File destinationFile) {
        // If there were no data, we should not even be trying to write anything
        assert (jsonData.length() > 0);
        try {
            String csv = CDL.toString(jsonData);
            FileUtils.writeStringToFile(destinationFile, csv, Charset.defaultCharset());
            logger.info("The following data was written:\n" + csv);
        } catch (IOException | JSONException e) {
            logger.severe("Unexpected error: " + e);
        }
    }

    /**
     * Creates a File object based on user's chosen directory.
     *
     * @return File object with a file name appended to the chosen directory
     */
    private File promptUserForDestination() {
        String destFileName = "programmerError.csv";
        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(primaryStage);
        return chosenDir == null ? null : new File(chosenDir, destFileName);
    }

    /**
     * Retrieves students' JSON data stored in ProgrammerError.
     *
     * @return JSONArray of student's data
     */
    private JSONArray getJsonData() {
        String resourceName = "data/programmerError.json";
        try {
            InputStream is = new FileInputStream(resourceName);
            String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(jsonTxt);
            return json.getJSONArray("students");
        } catch (IOException | JSONException e) {
            logger.severe("Error with the file!");
            return null;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.programmer.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            studentParticularPlaceholder.getChildren().clear();
            labResultListPanelPlaceholder.getChildren().clear();
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult instanceof HelpCommandResult) {
                handleHelp();
            } else if (commandResult instanceof ExitCommandResult) {
                handleExit();
            } else if (commandResult instanceof ShowCommandResult) {
                handleShowResult(((ShowCommandResult) commandResult).getTarget());
            } else if (commandResult instanceof DownloadCommandResult) {
                handleDownload();
            } else if (commandResult instanceof UploadCommandResult) {
                handleUpload();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

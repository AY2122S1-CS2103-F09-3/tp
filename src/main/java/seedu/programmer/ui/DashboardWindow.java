package seedu.programmer.ui;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.Logic;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.comparator.SortClassId;

/**
 * Dashboard window of student data.
 */
public class DashboardWindow extends PopupWindow {

    private static final String FXML = "DashboardWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Logic logic;
    private TreeMap<ClassId, Integer> labsUnmarkedMap;

    @FXML
    private StackPane overallStatsPlaceholder;

    @FXML
    private ScrollPane labsMarkedList;

    @FXML
    private VBox labVBox;


    /**
     * Creates a new DashboardWindow.
     *
     * @param root Stage to use as the root of the DashboardWindow.
     */
    public DashboardWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        update();
    }

    /**
     * Creates a new Dashboard Window.
     */
    public DashboardWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Fills number of students, classes and labs.
     */
    private void fillOverallStats() {
        overallStatsPlaceholder.getChildren().clear();
        ReadOnlyProgrammerError readOnlyPE = logic.getProgrammerError();
        ObservableList<Student> stuList = readOnlyPE.getStudentList();
        HashSet<ClassId> classList = fillClassList(stuList);
        labsUnmarkedMap = fillLabsUnmarkedMap(classList, stuList);
        int numStudents = stuList.size();
        int numClasses = classList.size();
        int numLabs = stuList.size() > 0 ? stuList.get(0).getLabList().size() : 0;

        String dataToDisplay = formatDataToDisplay(numStudents, numClasses, numLabs);
        Label label = new Label(dataToDisplay);
        label.getStylesheets().add("view/Dashboard.css");
        label.getStyleClass().add("overall-stats");
        overallStatsPlaceholder.getChildren().add(label);
    }

    private void fillLabsMarked() {
        labVBox.getChildren().clear();
        String labsMarked = formatLabsToDisplay(labsUnmarkedMap);
        Label labsLabel = new Label(labsMarked);
        labsLabel.getStylesheets().add("view/Dashboard.css");
        labsLabel.getStyleClass().add("labs-marked");
        labVBox.getChildren().add(labsLabel);
    }

    /**
     * Refreshes the dashboard window.
     */
    public void update() {
        fillOverallStats();
        fillLabsMarked();
    }

    private HashSet<ClassId> fillClassList(ObservableList<Student> stuList) {
        HashSet<ClassId> classList = new HashSet<>();
        stuList.forEach(s -> classList.add(s.getClassId()));
        return classList;
    }

    private TreeMap<ClassId, Integer> fillLabsUnmarkedMap(HashSet<ClassId> classList, ObservableList<Student> stuList) {
        TreeMap<ClassId, Integer> labsUnmarkedMap = new TreeMap<>(new SortClassId());
        classList.forEach(cid -> labsUnmarkedMap.put(cid, 0));

        for (Student s : stuList) {
            ObservableList<Lab> stuLabs = s.getLabList();
            for (Lab l : stuLabs) {
                if (!l.isMarked()) {
                    ClassId cid = s.getClassId();
                    labsUnmarkedMap.put(cid, labsUnmarkedMap.get(cid) + 1);
                }
            }
        }
        return labsUnmarkedMap;
    }

    private String formatDataToDisplay(int numStudents, int numClasses, int numLabs) {
        return "No. of students: " + numStudents + "\n"
                + "No. of classes: " + numClasses + "\n"
                + "No. of labs: " + numLabs + "\n\n";
    }

    private String formatLabsToDisplay(TreeMap<ClassId, Integer> labMap) {
        if (labMap.size() == 0) {
            return "You don't have any classes yet!";
        }

        StringBuilder dataToDisplay = new StringBuilder("No. of labs left to mark:\n");
        for (ClassId cid: labMap.keySet()) {
            String key = cid.toString();
            String value = labMap.get(cid).toString();
            dataToDisplay.append(key).append(": ").append(value).append("\n\n");
        }
        return dataToDisplay.append("Keep it going!  😄").toString();
    }
}

package seedu.programmer.model;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;


/**
 * Represents the in-memory model of ProgrammerError data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ProgrammerError programmerError;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given ProgrammerError and userPrefs.
     */
    public ModelManager(ReadOnlyProgrammerError programmerError, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(programmerError, userPrefs);

        logger.fine("Initializing with ProgrammerError: " + programmerError + " and user prefs " + userPrefs);

        this.programmerError = new ProgrammerError(programmerError);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.programmerError.getStudentList());
    }

    public ModelManager() {
        this(new ProgrammerError(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getProgrammerErrorFilePath() {
        return userPrefs.getProgrammerErrorFilePath();
    }

    @Override
    public void setProgrammerErrorFilePath(Path programmerErrorFilePath) {
        requireNonNull(programmerErrorFilePath);
        userPrefs.setProgrammerErrorFilePath(programmerErrorFilePath);
    }

    //=========== ProgrammerError ============================================================================

    @Override
    public void setProgrammerError(ReadOnlyProgrammerError programmerError) {
        this.programmerError.resetData(programmerError);
    }

    @Override
    public ReadOnlyProgrammerError getProgrammerError() {
        return programmerError;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return programmerError.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        programmerError.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        programmerError.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        programmerError.setStudent(target, editedStudent);
    }

    //=========== Filtered student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedProgrammerError}
    */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<DisplayableObject> getSelectedInformation() {
        return programmerError.getSelectedInformation();
    }

    @Override
    public Student getSelectedStudent() {
        return programmerError.getSelectedStudent();
    }


    @Override
    public void setSelectedStudent(Student target) {
        programmerError.setSelectedStudent(target);
    }


    @Override
    public void setSelectedLabs(List<Lab> labs) {
        programmerError.setSelectedLabs(labs);
    }

    @Override
    public void clearSelectedInformation() {
        programmerError.clearSelectedInformation();
    }

    @Override
    public void setLabsTracker(List<Lab> labs) {
        programmerError.setLabsTracker(labs);
    }

    @Override
    public void clearLabsTracker() {
        programmerError.clearLabsTracker();
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return programmerError.equals(other.programmerError)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}

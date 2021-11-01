package seedu.programmer.logic;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Student;
import seedu.programmer.storage.Storage;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ProgrammerError.
     *
     * @see seedu.programmer.model.Model#getProgrammerError()
     */
    ReadOnlyProgrammerError getProgrammerError();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns the selected labs. */
    ObservableList<DisplayableObject> getSelectedInformation();

    /**
     * Returns the user prefs' ProgrammerError file path.
     */
    Path getProgrammerErrorFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the Model associated with Logic.
     */
    Model getModel();

    /**
     * Returns the Storage associated with Logic
     */
    Storage getStorage();

    /**
     * Update ProgrammerError to the specified one.
     */
    void updateProgrammerError(ProgrammerError pe);

    /**
     * Update FilteredStudents to the specified one.
     */
    void updateFilteredStudents(Predicate<Student> predicate);

    /**
     * Save ProgrammerError to the storage.
     */
    void saveProgrammerError(ProgrammerError pe);
}

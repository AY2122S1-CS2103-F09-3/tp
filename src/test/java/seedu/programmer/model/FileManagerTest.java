package seedu.programmer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.Student;

class FileManagerTest {
    public static final String TEST_DATA_FOLDER = "src/test/data/FileManagerTest/";
    private static final String VALID_JSON = TEST_DATA_FOLDER + "programmerError.json";
    private static final String INVALID_JSON = TEST_DATA_FOLDER + "invalidProgrammerError.json";
    private static final String VALID_CSV = TEST_DATA_FOLDER + "validPE.csv";
    private static final String WRONG_NUM_COLS_CSV = TEST_DATA_FOLDER + "programmerError.csv";
    private static final String INVALID_FIELD_CSV = TEST_DATA_FOLDER + "invalidStudentIdPE.csv";
    private static final String MISSING_FIELD_CSV = TEST_DATA_FOLDER + "missingClassIdPE.csv";
    private static final String COMMAS_WITH_SPACES_CSV = TEST_DATA_FOLDER + "commasWithSpacesPE.csv";
    private static final String SWAPPED_COLUMN_NAME_CSV = TEST_DATA_FOLDER + "swappedColumnNamePE.csv";
    private static final String WRONG_COLUMN_ORDER_CSV = TEST_DATA_FOLDER + "wrongColumnOrderPE.csv";
    private static final String MISSING_HEADERS_CSV = TEST_DATA_FOLDER + "missingHeadersPE.csv";
    private static final int DEFAULT_NUM_OF_STUDENTS = 36;
    private FileManager fm;

    @BeforeEach
    public void setUpTests() {
        fm = new FileManager();
    }

    @Test
    public void getJsonData_validJson_returnsCorrectNumberOfElements() {
        JSONArray result = fm.getJsonData(VALID_JSON);
        assertEquals(result.length(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getJsonData_invalidJson_returnsNull() {
        JSONArray result = fm.getJsonData(INVALID_JSON);
        assertNull(result);
    }

    @Test
    public void getStudentsFromCsv_validCsv_returnsCorrectNumberOfStudents() throws IOException, IllegalValueException {
        File testFile = new File(VALID_CSV);
        List<Student> stuList = fm.getStudentsFromCsv(testFile);
        assertEquals(stuList.size(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getStudentsFromCsv_wrongNumberOfColumns_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(WRONG_NUM_COLS_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_invalidField_throwsIllegalArgumentException() {
        String expectedMessage = "Student ID should be of the form AXXXXXXXY where X is a non-negative integer "
                + "and Y is the last character of your student ID."
                + "\nFor example: A0212345T";
        File testFile = new File(INVALID_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_missingField_throwsIllegalArgumentException() {
        String expectedMessage = "Class ID should only contain 3 alphanumeric characters that begins with B"
                               + " followed by class number (eg. B01 or B11), and it should not be blank";
        File testFile = new File(MISSING_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_commasWithSpaces_throwsIllegalArgumentException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(COMMAS_WITH_SPACES_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_swappedColumnName_throwsIllegalArgumentException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(SWAPPED_COLUMN_NAME_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_wrongOrder_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(WRONG_COLUMN_ORDER_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_missingHeaders_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(MISSING_HEADERS_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> fm.getStudentsFromCsv(testFile));
    }
}

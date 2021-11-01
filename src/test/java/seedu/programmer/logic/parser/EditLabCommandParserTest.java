
package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_LAB_TOTAL;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_NEW_LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_TOTAL2;
import static seedu.programmer.logic.commands.CommandTestUtil.NEW_LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO2;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE2;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.EditLabCommand;
import seedu.programmer.model.student.Lab;
import seedu.programmer.testutil.LabBuilder;

public class EditLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE);

    private EditLabCommandParser parser = new EditLabCommandParser();

    @Test
    public void parse_missingCompulsoryPrefix_failure() {
        // missing labNum prefix
        assertParseFailure(parser,
                VALID_LAB_NO + NEW_LAB_NUM + LAB_TOTAL2,
                String.format(MESSAGE_MISSING_ARGUMENT, EditLabCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid labNum
        assertParseFailure(parser,
                INVALID_LAB_NUM + NEW_LAB_NUM + LAB_TOTAL2,
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-1]", EditLabCommand.MESSAGE_USAGE));

        // invalid newLabNum
        assertParseFailure(parser,
                LAB_NUM + INVALID_NEW_LAB_NUM + LAB_TOTAL2,
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-2]", EditLabCommand.MESSAGE_USAGE));

        // invalid labTotal
        assertParseFailure(parser,
                LAB_NUM + NEW_LAB_NUM + INVALID_LAB_TOTAL,
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-10]", EditLabCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Lab originalLab = new LabBuilder().withLabNum(VALID_LAB_NO).withTotal(VALID_TOTAL_SCORE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LAB_NUM + NEW_LAB_NUM + LAB_TOTAL2,
                new EditLabCommand(originalLab, VALID_LAB_NO2, VALID_TOTAL_SCORE2));
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Lab originalLab = new LabBuilder().withLabNum(VALID_LAB_NO).withTotal(VALID_TOTAL_SCORE).build();

        // newLabTitle only
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LAB_NUM + NEW_LAB_NUM,
                new EditLabCommand(originalLab, VALID_LAB_NO2));

        // newTotalScore only
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LAB_NUM + LAB_TOTAL2,
                new EditLabCommand(originalLab, VALID_TOTAL_SCORE2));
    }

}

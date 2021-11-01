package seedu.programmer.testutil;

import seedu.programmer.model.student.Lab;

public class LabBuilder {
    public static final int DEFAULT_TITLE = 1;
    public static final int DEFAULT_VALUE = 0;
    public static final int DEFAULT_TOTAL = 20;

    private int labNum;
    private int value;
    private int total;

    /**
     * Creates a {@code labBuilder} with the default details.
     */
    public LabBuilder() {
        labNum = DEFAULT_TITLE;
        value = DEFAULT_VALUE;
        total = DEFAULT_TOTAL;
    }

    /**
     * Initializes the labBuilder with the data of {@code labToCopy}.
     */
    public LabBuilder(Lab labToCopy) {
        labNum = labToCopy.getLabNum();
        value = labToCopy.getActualScore();
        total = labToCopy.getTotalScore();
    }

    /**
     * Sets the {@code labNum} of the {@code lab} that we are building.
     */
    public LabBuilder withLabNum(int labNum) {
        this.labNum = labNum;
        return this;
    }

    /**
     * Sets the {@code value} of the {@code lab} that we are building.
     */
    public LabBuilder withResult(int value) {
        this.value = value;
        return this;
    }

    /**
     * Sets the {@code total} of the {@code lab} that we are building.
     */
    public LabBuilder withTotal(int total) {
        this.total = total;
        return this;
    }

    public Lab build() {
        return new Lab(labNum, value, total);
    }
}

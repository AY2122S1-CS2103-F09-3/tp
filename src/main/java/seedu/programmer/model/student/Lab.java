package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

public class Lab implements DisplayableObject {

    public static final String MESSAGE_LAB_SCORE_AND_LAB_NUMBER_REQUIREMENT =
            "Both Lab Number and Score to be should be provided together.";
    public static final String MESSAGE_LAB_NUMBER_CONSTRAINT =
            "Lab number should be between 1 and 13 (inclusive).";
    public static final String MESSAGE_LAB_SCORE_CONSTRAINT = "Lab score should be a non-negative integer or " +
            "a unmarked placeholder(-1).";
    public static final String MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT =
            "Lab total score should be between 1 and 100 (inclusive)";

    public static final String MESSAGE_LAB_ALREADY_EXISTS = "%1$s already exists.";
    public static final String MESSAGE_LAB_NOT_EXISTS = "%1$s doesn't exist.";

    private LabNum labNum;
    private LabResult actualScore;
    private LabTotal totalScore;

    /**
     * Constructs a marked Lab Object.
     * @param labNum the labNum of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(LabNum labNum, LabResult actualScore, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * Constructs a unmarked Lab Object.
     * @param labNum the labNum of the lab
     * @param totalScore the total score */
    public Lab(LabNum labNum, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = new LabResult(LabResult.getPlaceholder());
        this.totalScore = totalScore;
    }

    /**
     * @param labNum the labNum of the lab
     * */
    public Lab(LabNum labNum) {
        this.labNum = labNum;
    }

    public Lab() {}

    public LabNum getLabNum() {
        return labNum;
    }

    public LabResult getLabResult() {
        return actualScore;
    }

    public LabTotal getLabTotal() {
        return totalScore;
    }

    public int getLabNumValue() {
        return labNum.getLabNum();
    }

    public int getLabResultValue() {
        return actualScore.getLabResult();
    }

    public int getLabTotalValue() {
        return totalScore.getLabTotalScore();
    }

    public void updateActualScore(LabResult value) {
        this.actualScore = value;
    }

    /**
     * Updates the labNum of the lab
     * @param newLabNum the new lab number
     */
    public void updateLabNum(LabNum newLabNum) {
        if (newLabNum.getLabNum() > 0) {
            this.labNum = newLabNum;
        }
    }

    /**
     * Updates the totalScore of the lab
     * @param total new total score
     */
    public void updateTotal(LabTotal total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    public boolean isMarked() {
        return !(actualScore.getLabResult().equals(LabResult.getPlaceholder()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNum.getLabNum().equals(((Lab) other).getLabNum().getLabNum()));
    }

    /**
     * check if the labNum, labResult and labTotal are all identical.
     */
    public boolean isIdenticalLab(Lab otherLab) {
        return labNum.equals(otherLab.getLabNum())
                && actualScore.equals(otherLab.getLabResult())
                && totalScore.equals(otherLab.getLabTotal());
    }

    @Override
    public String toString() {
        return "Lab " + this.labNum;
    }

    /**
     * Make a copy of an existing Lab.
     * @return a copy of the lab
     */
    public Lab copy() {
        return new Lab(labNum, actualScore, totalScore);
    }
}

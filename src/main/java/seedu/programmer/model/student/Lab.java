package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

public class Lab {

    public static final String LAB_SCORE_MESSAGE_CONSTRAINTS = "The total score should be a positive value.";

    private int labNum;
    private Double actualScore;
    private Double totalScore;

    /**
     * @param labNum the labNum of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(int labNum, Double actualScore, Double totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * @param labNum the labNum of the lab
     * @param totalScore the total score */
    public Lab(int labNum, Double totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = Double.NaN;
        this.totalScore = totalScore;
    }

    /**
     * @param labNum the labNum of the lab
     * */
    public Lab(int labNum) {
        this.labNum = labNum;
    }

    public Lab(){}

    public int getLabNum() {
        return labNum;
    }

    public Double getActualScore() {
        return actualScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void updateActualScore(Double value) {
        this.actualScore = value;
    }

    /**
     * Updates the labNum of the lab
     * @param newLabNum
     */
    public void updateLabNum(int newLabNum) {
        if (newLabNum > 0) {
            this.labNum = newLabNum;
        }
    }

    /**
     * Updates the totalScore of the lab
     * @param total new total score
     */
    public void updateTotal(Double total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    public boolean isMarked() {
        return !actualScore.equals(Double.NaN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNum == ((Lab) other).getLabNum());
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidScore (Double score) {
        return score >= 0;
    }

    @Override
    public String toString() {
        return "Lab " + this.labNum;
    }
}

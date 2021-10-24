package seedu.programmer.model.student;

public class Lab {
    private String title;
    private Double actualScore;
    private Double totalScore;

    /**
     * @param title the title of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(String title, Double actualScore, Double totalScore) {
        this.title = title;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * @param title the title of the lab
     * @param totalScore the total score
     * */
    public Lab(String title, Double totalScore) {
        this.title = title;
        this.actualScore = Double.NaN;
        this.totalScore = totalScore;
    }

    /**
     * @param title the title of the lab
     * */
    public Lab(String title) {
        this.title = title;
    }

    public Lab(){}

    public String getTitle() {
        return title;
    }

    public Double getActualScore() {
        return actualScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public Double updateActualScore(Double value) {
        return this.actualScore = value;
    }

    public boolean isMarked() {
        return !actualScore.equals(Double.NaN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && title.equals(((Lab) other).getTitle()));
    }

    @Override
    public String toString() {
        return this.title;
    }
}

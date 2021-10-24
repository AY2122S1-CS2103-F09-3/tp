package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

public class UploadCommandResult extends CommandResult {
     private Model model;

    public UploadCommandResult(String feedbackToUser, Model model) {
        super(feedbackToUser);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}

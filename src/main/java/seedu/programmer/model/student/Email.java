package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's Email in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format eXXXXXXX@u.nus.edu"
            + " and adhere to the following constraints:\n"
            + "1. The local-part should only contain eXXXXXXX where X are numeric characters"
            + "2. This is followed by a '@u.nus.edu'";

    public static final String VALIDATION_REGEX = "e" + "\\d{7}+" + "@u.nus.edu";
    public final String email;

    /**
     * Constructs a {@code Email}.
     *
     * @param email A valid Email.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        this.email = email;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidEmail (String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && email.equals(((Email) other).email)); // state check
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

}

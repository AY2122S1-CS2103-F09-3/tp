package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's Email in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String MESSAGE_CONSTRAINTS = "Emails should be in the format: eXXXXXX@u.nus.edu "
            + "(case-insensitive), where X represents a digit from 0 to 9";

    private static final String VALIDATION_REGEX = "e" + "\\d{7}+" + "@u.nus.edu";
    private final String email;

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

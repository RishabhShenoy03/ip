package jongbot.exceptions;

public class MissingDeadlineException extends JongExceptions{
    /**
     * Constructs a MissingDeadlineException with a default error message.
     */
    public MissingDeadlineException() {
        super("Please include /by for any deadlines!!\n"
            + "For example: \"deadline homework /by tomorrow\"");
    }
}

package jongbot.exceptions;

public class EmptyDeadlineDescriptionException extends JongExceptions {
    /**
     * Constructs an EmptyDeadlineDescriptionException with a default error message.
     */
    public EmptyDeadlineDescriptionException() {
        super("Deadline description cannot be empty.\nTry \"deadline homework /by 8pm\"");
    }
}

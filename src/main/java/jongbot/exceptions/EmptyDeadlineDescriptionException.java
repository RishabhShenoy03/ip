package jongbot.exceptions;

public class EmptyDeadlineDescriptionException extends JongExceptions {
    /**
     * Creates an EmptyDeadlineDescriptionException with a default message.
     */
    public EmptyDeadlineDescriptionException() {
        super("Deadline description cannot be empty.\nTry \"deadline homework /by 8pm\"");
    }
}

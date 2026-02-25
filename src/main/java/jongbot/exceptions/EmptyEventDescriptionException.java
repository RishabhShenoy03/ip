package jongbot.exceptions;

public class EmptyEventDescriptionException extends JongExceptions{
    /**
     * Constructs an EmptyEventDescriptionException with a default error message.
     */
    public EmptyEventDescriptionException() {
        super("Event description cannot be empty.\nTry \"event homework /from 10am /to 1pm\"");
    }
}

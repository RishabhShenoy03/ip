package jongbot.exceptions;

public class EmptyEventDescriptionException extends JongExceptions{
    /**
     * Creates an EmptyEventDescriptionException with a default message.
     */
    public EmptyEventDescriptionException() {
        super("Event description cannot be empty.\nTry \"event homework /from 10am /to 1pm\"");
    }
}

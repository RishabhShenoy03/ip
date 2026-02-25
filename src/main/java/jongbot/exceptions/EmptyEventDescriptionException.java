package jongbot.exceptions;

public class EmptyEventDescriptionException extends JongExceptions{
    public EmptyEventDescriptionException() {
        super("Event description cannot be empty.\nTry \"event homework /from 10am /to 1pm\"");
    }
}

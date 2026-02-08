package jongbot.exceptions;

public class EmptyDeadlineDescriptionException extends JongExceptions {
    public EmptyDeadlineDescriptionException() {
        super("Deadline description cannot be empty.\nTry \"deadline homework /by 8pm\"");
    }
}

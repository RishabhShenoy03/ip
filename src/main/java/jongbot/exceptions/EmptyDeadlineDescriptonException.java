package jongbot.exceptions;

public class EmptyDeadlineDescriptonException extends JongExceptions {

    public EmptyDeadlineDescriptonException() {
        super("Deadline description cannot be empty.\nTry \"deadline homework /by tomorrow\"");
    }
}

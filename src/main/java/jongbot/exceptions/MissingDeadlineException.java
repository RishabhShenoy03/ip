package jongbot.exceptions;

public class MissingDeadlineException extends JongExceptions{
    public MissingDeadlineException() {
        super("Please include /by for any deadlines!!\n"
            + "For example: \"deadline homework /by tomorrow\"");
    }
}

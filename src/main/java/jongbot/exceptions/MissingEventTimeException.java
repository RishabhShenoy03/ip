package jongbot.exceptions;

public class MissingEventTimeException extends JongExceptions{
    /**
     * Constructs a MissingEventTimeException with a default error message.
     */
    public MissingEventTimeException() {
        super("Please include /from and /to for any events!!\n"
            + "For example: \"event homework /from 10am /to 1pm\"");
    }
}

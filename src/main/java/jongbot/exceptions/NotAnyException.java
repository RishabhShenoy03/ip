package jongbot.exceptions;

public class NotAnyException extends JongExceptions{
    /**
     * Constructs a NotAnyException with a default error message.
     */
    public NotAnyException(){
        super("I don't understand what you mean, please try again\n"
            + "Insert with \"todo\" / \"deadline\" / \"event\"");
    }
}

package jongbot.exceptions;

public class NotAnyException extends JongExceptions{
    /**
     * Creates a NotAnyException with a default message.
     */
    public NotAnyException(){
        super("I don't understand what you mean, please try again\n"
            + "Insert with \"todo\" / \"deadline\" / \"event\"");
    }
}

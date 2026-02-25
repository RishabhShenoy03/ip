package jongbot.exceptions;

public class NotAnyException extends JongExceptions{
    public NotAnyException(){
        super("I don't understand what you mean, please try again\n"
            + "Insert with \"todo\" / \"deadline\" / \"event\"");
    }
}

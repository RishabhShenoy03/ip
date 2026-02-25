package jongbot.exceptions;

public class EmptyListException extends JongExceptions {
    /**
     * Constructs an EmptyListException with a default error message.
     */
    public EmptyListException() {
        super("List is empty. Insert some stuff bro :P");
    }
}

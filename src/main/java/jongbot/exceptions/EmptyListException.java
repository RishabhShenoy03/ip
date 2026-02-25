package jongbot.exceptions;

public class EmptyListException extends JongExceptions {
    /**
     * Creates an EmptyListException with a default message.
     */
    public EmptyListException() {
        super("List is empty. Insert some stuff bro :P");
    }
}

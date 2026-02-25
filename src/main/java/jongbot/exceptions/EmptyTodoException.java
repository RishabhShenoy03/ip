package jongbot.exceptions;

public class EmptyTodoException extends JongExceptions{
    /**
     * Constructs an EmptyTodoException with a default error message.
     */
    public EmptyTodoException() {
        super("Todo description cannot be empty. Try \"todo homework\"");
    }
}

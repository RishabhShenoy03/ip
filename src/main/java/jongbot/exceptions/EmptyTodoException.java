package jongbot.exceptions;

public class EmptyTodoException extends JongExceptions{
    /**
     * Creates an EmptyTodoException with a default message.
     */
    public EmptyTodoException() {
        super("Todo description cannot be empty. Try \"todo homework\"");
    }
}

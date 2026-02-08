package jongbot.exceptions;

public class EmptyTodoException extends JongExceptions{
    public EmptyTodoException() {
        super("Todo description cannot be empty. Try \"todo homework\"");
    }
}

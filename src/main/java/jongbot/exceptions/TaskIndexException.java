package jongbot.exceptions;

public class TaskIndexException extends JongExceptions{

    /**
     * Constructs a TaskIndexException with a default error message.
     */
    public TaskIndexException() {
        super("Invalid task index. use \"list\" to see all tasks");
    }
}

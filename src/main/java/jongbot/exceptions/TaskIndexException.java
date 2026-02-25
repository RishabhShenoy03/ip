package jongbot.exceptions;

public class TaskIndexException extends JongExceptions{

    /**
     * Creates a TaskIndexException with a default message.
     */
    public TaskIndexException() {
        super("Invalid task index. use \"list\" to see all tasks");
    }
}

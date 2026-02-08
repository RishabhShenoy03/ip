package jongbot.exceptions;

public class TaskIndexException extends JongExceptions{

    public TaskIndexException() {
        super("Invalid task index. use \"list\" to see all tasks");
    }
}

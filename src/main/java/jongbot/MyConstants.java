package jongbot;

public class MyConstants {
    public static final String TICK = "✔";
    public static final String WELCOME_MESSAGE = """
            Hello! My name is JongBot
            I am here to help you manage your todos, deadlines, and events.
            How may I help you?
            Type "help" to see all commands""";

    public static final String HELP_MESSAGE = """
            Use "todo", "deadline", "event" to add a task to the list
            Use "list" to see all tasks
            Use "mark" / "unmark" / "delete" followed by the task index to do... exactly that
            Use "find <word> to find tasks containing that word""";

    public static void newline() {
        System.out.println();
    }

    public static void dashLine() {
        System.out.println("----------------------------------------");
    }
}

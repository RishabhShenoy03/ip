import java.util.Scanner;

public class JongBot {

    public static int numLines = 0;
    public static final int MAX_SIZE = 100;

    public static Task[] list = new Task[MAX_SIZE];

    public static void newline() {
        System.out.println();
    }

    public static void dashLine() {
        System.out.println("----------------------------------------");
    }

    public static void printList(){
        System.out.println("Here's your list:");
        for (int i = 0; i < numLines; i ++){
            if (list[i].description.equals("list")){
                continue;
            }
            System.out.println( i+1 + ":" + list[i]);
        }
    }

    public static void main(String[] args) {
        welcomeMessage();
        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            String trimmed = input.trim();
            String command;
            String arguments;
            String description;

            int firstSpace = trimmed.indexOf(' ');
            if (firstSpace == -1) {
                command = trimmed;
                arguments = "";
            } else {
                command = trimmed.substring(0, firstSpace);
                arguments = trimmed.substring(firstSpace + 1).trim();
            }

            newline();
            dashLine();

            switch (command) {
            case "todo":
                handleTodo(arguments);
                break;

            case "deadline":
                handleDeadline(arguments);
                break;

            case "event":
                handleEvent(arguments);
                break;

            case "bye":
                handleBye();
                return;

            case "list":
                if (numLines == 0){
                    System.out.println("List is empty, insert some tasks!");
                    break;
                }
                printList();
                break;

            case "mark": {
                markTask(arguments);
                break;
            }

            case "unmark": {
                unmarkTask(arguments);
                break;
            }

            default:
                // insert into list for normal input
                System.out.println("I don't understand what you mean, please try again");
                System.out.println("Insert with \"todo\" / \"deadline\" / \"event\"");

                break;
            }
            dashLine();
            newline();
        }
    }

    private static void handleTodo(String arguments) {
        Todo todo = new Todo(arguments);
        list[numLines] = todo;
        numLines++;
        echoTodo(arguments);
    }

    private static void handleBye() {
        System.out.println("Bye bye! See you soon!");
        dashLine();
    }

    private static void handleEvent(String arguments) {
        String description;
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1){
            System.out.println("Please include both /from and /to");
            return;
        }

        description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();
        Event event = new Event(description, from, to);
        list[numLines] = event;
        numLines++;
        echoEvent(description, from, to);
    }

    private static void handleDeadline(String arguments) {
        String description;
        int byIndex = arguments.indexOf("/by");

        if (byIndex == -1){
            System.out.println("Please include /by");
            return;
        }

        description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 3).trim();
        Deadline deadline = new Deadline(description, by);
        list[numLines] = deadline;
        numLines++;

        echoDeadline(description, by);
    }

    private static void unmarkTask(String arguments) {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > numLines || taskIndex < 1){
            System.out.println("This task does not exist, use 'list' first to check task numbers");
        } else {
            list[taskIndex - 1].unmarkTask();
            System.out.println("Task " + taskIndex + " has been unmarked");
        }
    }

    private static void markTask(String arguments) {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > numLines || taskIndex < 1){
            System.out.println("This task does not exist, use 'list' first to check task numbers");
        } else {
            list[taskIndex - 1].markTask();
            System.out.println("Task " + taskIndex + " has been marked");
        }
    }

    private static void echoEvent(String description, String from, String to) {
        System.out.println("Adding this task to the list:");
        System.out.println("[E][ ] " + description + " (from: " + from + ", to: " + to + ")");
        System.out.println("Number of tasks in list: " + numLines);
    }

    private static void echoDeadline(String description, String by) {
        System.out.println("Adding this task to the list:");
        System.out.println("[D][ ] " + description + " (do by: " + by + ")");
        System.out.println("Number of tasks in list: " + numLines);
    }

    private static void echoTodo(String arguments) {
        System.out.println("Adding this task to the list:");
        System.out.println("[T][ ] " + arguments);
        System.out.println("Number of tasks in list: " + numLines);
    }

    private static void welcomeMessage() {
        newline();
        System.out.println("Hello! My name is Jongbot");
        System.out.println("How may I help you?");
        newline();
    }
}
import java.util.Scanner;

public class JongBot {
    public static void newline() {
        System.out.println();
    }

    public static void dashLine() {
        System.out.println("----------------------------------------");
    }

    public static void printList(Task[] list, int numLines){
        newline();
        System.out.println("Here's your list:");
        for (int i = 0; i < numLines; i ++){
            if (list[i].description.equals("list")){
                continue;
            }
            System.out.println( i+1 + ":" + list[i]);
        }
    }

    public static void main(String[] args) {
        newline();
        System.out.println("Hello! My name is Jongbot");
        System.out.println("How may I help you?");
        newline();

        String input;
        Task[] list = new Task[100];
        Scanner in = new Scanner(System.in);

        int numLines = 0;
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
                Todo todo = new Todo(arguments);
                list[numLines] = todo;
                numLines++;

                System.out.println("Adding this task to the list:");
                System.out.println("[T][ ]" + arguments);
                System.out.println("Number of tasks in list: " + numLines);
                break;

            case "deadline":
                int byIndex = arguments.indexOf("/by");

                if (byIndex == -1){
                    System.out.println("Please include /by");
                    break;
                }

                description = arguments.substring(0, byIndex).trim();
                String by = arguments.substring(byIndex + 3).trim();
                Deadline deadline = new Deadline(description, by);
                list[numLines] = deadline;
                numLines++;

                System.out.println("Adding this task to the list:");
                System.out.println("[D][ ]" + description + "(do by: " + by + ")");
                System.out.println("Number of tasks in list: " + numLines);
                break;

            case "event":
                int fromIndex = arguments.indexOf("/from");
                int toIndex = arguments.indexOf("/to");

                if (fromIndex == -1 || toIndex == -1){
                    System.out.println("Please include both /from and /to");
                    break;
                }

                description = arguments.substring(0, fromIndex).trim();
                String from = arguments.substring(fromIndex + 5, toIndex).trim();
                String to = arguments.substring(toIndex + 3).trim();
                Event event = new Event(description, from, to);
                list[numLines] = event;
                numLines++;
                System.out.println("Adding this task to the list:");
                System.out.println("[E][ ] " + description + " (from: " + from + ", to: " + to + ")");
                System.out.println("Number of tasks in list: " + numLines);
                break;

            case "bye":
                System.out.println("Bye bye! See you soon!");
                newline();
                dashLine();
                return;

            case "list":
                if (numLines == 0){
                    System.out.println("List is empty, insert some tasks!");
                    break;
                }
                printList(list, numLines);
                break;

            case "mark": {
                int taskIndex = Integer.parseInt(arguments);
                if (taskIndex > numLines || taskIndex < 1){
                    System.out.println("This task does not exist, use 'list' first to check task numbers");
                } else {
                    list[taskIndex - 1].markTask();
                    System.out.println("Task " + taskIndex + " has been marked");
                }
                newline();
                break;
            }

            case "unmark": {
                int taskIndex = Integer.parseInt(arguments);
                if (taskIndex > numLines || taskIndex < 1){
                    System.out.println("This task does not exist, use 'list' first to check task numbers");
                } else {
                    list[taskIndex - 1].unmarkTask();
                    System.out.println("Task " + taskIndex + " has been unmarked");
                }
                newline();
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
}
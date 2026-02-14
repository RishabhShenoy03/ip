package jongbot;

import java.util.Scanner;
import jongbot.exceptions.EmptyDeadlineDescriptionException;
import jongbot.exceptions.EmptyEventDescriptionException;
import jongbot.exceptions.EmptyListException;
import jongbot.exceptions.EmptyTodoException;
import jongbot.exceptions.JongExceptions;
import jongbot.exceptions.MissingDeadlineException;
import jongbot.exceptions.MissingEventTimeException;
import jongbot.exceptions.NotAnyException;
import jongbot.exceptions.TaskIndexException;

import java.util.ArrayList;

public class JongBot {

    public static ArrayList<Task> list = new ArrayList<>();

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
            try {
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
                    handleList();
                    break;

                case "mark": {
                    markTask(arguments);
                    break;
                }

                case "unmark": {
                    unmarkTask(arguments);
                    break;
                }

                case "delete": {
                    deleteTask(arguments);
                    break;
                }
                case "help":{
                    help();
                    break;
                }

                default:
                    // none of the accepted commands
                    throw new NotAnyException();
                }
            } catch (JongExceptions e) {
                System.out.println(e.getMessage());
            }
            dashLine();
            newline();
        }
    }

    private static void handleTodo(String arguments) throws JongExceptions {
        if (arguments.isBlank()) {
            throw new EmptyTodoException();
        }

        Todo todo = new Todo(arguments);
        list.add(todo);
        echoTodo(arguments);
    }

    private static void handleBye() {
        System.out.println("Bye bye! See you soon!");
        dashLine();
    }

    private static void handleEvent(String arguments) throws JongExceptions {
        String description;
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        if (arguments.isBlank()) {
            throw new EmptyEventDescriptionException();
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new MissingEventTimeException();
        }

        description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();
        Event event = new Event(description, from, to);
        list.add(event);
        echoEvent(description, from, to);
    }

    private static void handleDeadline(String arguments) throws JongExceptions {
        String description;
        int byIndex = arguments.indexOf("/by");

        if (arguments.isBlank()) {
            throw new EmptyDeadlineDescriptionException();
        }
        if (byIndex == -1) {
            throw new MissingDeadlineException();
        }

        description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 3).trim();
        Deadline deadline = new Deadline(description, by);
        list.add(deadline);

        echoDeadline(description, by);
    }

    private static void unmarkTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.get(taskIndex - 1).unmarkTask();
        System.out.println("Task " + taskIndex + " has been unmarked");

    }

    private static void markTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.get(taskIndex - 1).markTask();
        System.out.println("Task " + taskIndex + " has been marked");

    }

    public static void newline() {
        System.out.println();
    }

    public static void dashLine() {
        System.out.println("----------------------------------------");
    }

    public static void handleList() throws JongExceptions {
        if (list.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println("Here's your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.equals("list")) {
                continue;
            }
            System.out.println(i + 1 + ":" + list.get(i));
        }
    }

    public static void deleteTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.remove(taskIndex - 1);
        System.out.println("Task " + taskIndex + " has been deleted");
    }

    private static void help(){
        System.out.println("Use \"todo\", \"deadline\", \"event\" to add a task to the list");
        System.out.println("Use \"list\" to see all tasks");
        System.out.println("Use \"mark\" / \"unmark\" / \"delete\" followed by the task index to do... exactly that");

    }

    private static void echoEvent(String description, String from, String to) {
        System.out.println("Adding this task to the list:");
        System.out.println("[E][ ] " + description + " (from: " + from + ", to: " + to + ")");
        System.out.println("Number of tasks in list: " + list.size());
    }

    private static void echoDeadline(String description, String by) {
        System.out.println("Adding this task to the list:");
        System.out.println("[D][ ] " + description + " (do by: " + by + ")");
        System.out.println("Number of tasks in list: " + list.size());
    }

    private static void echoTodo(String arguments) {
        System.out.println("Adding this task to the list:");
        System.out.println("[T][ ] " + arguments);
        System.out.println("Number of tasks in list: " + list.size());
    }

    private static void welcomeMessage() {
        newline();
        System.out.println("Hello! My name is JongBot");
        System.out.println("How may I help you?");
        System.out.println("Type \"help\" to see all commands");
        newline();
    }
}

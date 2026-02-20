package jongbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

import static jongbot.MyConstants.dashLine;
import static jongbot.MyConstants.newline;

public class JongBot {

    private static ArrayList<Task> list = new ArrayList<>();
    private static final String tasksFilePath = "data/tasks.txt";
    private static File f = new File(tasksFilePath);

    public static void main(String[] args) {
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());

        welcomeMessage();

        ensureFileExists();
        loadTasksFromFile();

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
        addTaskToFile(todo);
        echoTodo(arguments);
    }

    private static void handleBye() {
        System.out.println("Bye bye! See you soon!");
        dashLine();
    }

    private static void handleEvent(String arguments) throws JongExceptions {
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        if (arguments.isBlank()) {
            throw new EmptyEventDescriptionException();
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new MissingEventTimeException();
        }

        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();
        Event event = new Event(description, from, to);
        list.add(event);
        addTaskToFile(event);
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
        addTaskToFile(deadline);

        echoDeadline(description, by);
    }

    private static void unmarkTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.get(taskIndex - 1).unmarkTask();
        writeWholeListToFile();
        System.out.println("Task " + taskIndex + " has been unmarked");
    }

    private static void markTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.get(taskIndex - 1).markTask();
        writeWholeListToFile();
        System.out.println("Task " + taskIndex + " has been marked");

    }

    private static void writeWholeListToFile() {
        try {
            FileWriter fw = new FileWriter(tasksFilePath);
            for (Task task : list) {
                fw.write(task.toDataString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }

    public static void handleList() throws JongExceptions {
        if (list.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println("Here's your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ":" + list.get(i));
        }
    }

    public static void deleteTask(String arguments) throws JongExceptions {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > list.size() || taskIndex < 1) {
            throw new TaskIndexException();
        }
        list.remove(taskIndex - 1);
        writeWholeListToFile();
        System.out.println("Task " + taskIndex + " has been deleted");
    }

    private static void help(){
        System.out.println(MyConstants.HELP_MESSAGE);
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
        System.out.println(MyConstants.WELCOME_MESSAGE);
        newline();
    }

    private static void handleFileLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
            }
            break;
        }

        if (task != null) {
            if (isDone) {
                task.markTask();
            }
            list.add(task);
        }
    }

    private static void loadTasksFromFile(){
        File file = new File(tasksFilePath );
        if (!file.exists()) {
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                handleFileLine(sc.nextLine());
            }
            try {
                handleList(); // print previously saved list after parsed
            } catch (JongExceptions e) {
                System.out.println(e.getMessage());
            }
            dashLine();
            newline();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + tasksFilePath);
        }
    }

    private static void ensureFileExists() {
        try {
            File file = new File(tasksFilePath);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException("Failed to create directory: "
                            + parent.getAbsolutePath());
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: "
                            + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Critical error initializing storage", e);
        }
    }

    private static void addTaskToFile(Task task) {
        try {
            FileWriter fw = new FileWriter(tasksFilePath,true);
            fw.write(task.toDataString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
}

package jongbot;

import java.util.Scanner;

import jongbot.exceptions.EmptyListException;
import jongbot.exceptions.JongExceptions;

import static jongbot.MyConstants.dashLine;
import static jongbot.MyConstants.newline;

public class Ui {
    private final Scanner in;

    /**
     * Creates a UI instance bound to standard input/output.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a full command line from the user.
     *
     * @return Raw user input.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        newline();
        System.out.println(MyConstants.WELCOME_MESSAGE);
        newline();
    }

    /**
     * Prints the help message.
     */
    public void showHelp() {
        System.out.println(MyConstants.HELP_MESSAGE);
    }

    /**
     * Prints a separator line.
     */
    public void showLine() {
        dashLine();
    }

    /**
     * Prints an error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Prints all tasks in the given list.
     *
     * @param tasks Task list to show.
     * @throws JongExceptions If the list is empty.
     */
    public void showList(TaskList tasks) throws JongExceptions {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        System.out.println("Here's your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ":" + tasks.getTasks().get(i));
        }
    }

    /**
     * Prints a message after adding a task.
     *
     * @param task Task that was added.
     * @param size Current list size.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Adding this task to the list:");
        System.out.println(task);
        System.out.println("Number of tasks in list: " + size);
    }

    /**
     * Prints a message after marking a task.
     *
     * @param index Task index (1-based).
     */
    public void showMarked(int index) {
        System.out.println("Task " + index + " has been marked");
    }

    /**
     * Prints a message after unmarking a task.
     *
     * @param index Task index (1-based).
     */
    public void showUnmarked(int index) {
        System.out.println("Task " + index + " has been unmarked");
    }

    /**
     * Prints a message after deleting a task.
     *
     * @param index Task index (1-based).
     */
    public void showDeleted(int index) {
        System.out.println("Task " + index + " has been deleted");
    }

    /**
     * Prints the goodbye message.
     */
    public void showBye() {
        System.out.println("Bye bye! See you soon!");
    }
}

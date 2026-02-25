package jongbot;

import java.util.ArrayList;
import java.util.Scanner;

import jongbot.exceptions.EmptyListException;
import jongbot.exceptions.JongExceptions;

import static jongbot.MyConstants.dashLine;
import static jongbot.MyConstants.newline;

public class Ui {
    private final Scanner in;

    /**
     * Constructs a Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        newline();
        System.out.println(MyConstants.WELCOME_MESSAGE);
        newline();
    }

    /**
     * Displays the help message showing available commands.
     */
    public void showHelp() {
        System.out.println(MyConstants.HELP_MESSAGE);
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        dashLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message when tasks fail to load from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The TaskList to display.
     * @throws JongExceptions If the task list is empty.
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
     * Displays a confirmation message when a task is added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Adding this task to the list:");
        System.out.println(task);
        System.out.println("Number of tasks in list: " + size);
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     *
     * @param index The index of the marked task.
     */
    public void showMarked(int index) {
        System.out.println("Task " + index + " has been marked");
    }

    /**
     * Displays a confirmation message when a task is unmarked.
     *
     * @param index The index of the unmarked task.
     */
    public void showUnmarked(int index) {
        System.out.println("Task " + index + " has been unmarked");
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param index The index of the deleted task.
     */
    public void showDeleted(int index) {
        System.out.println("Task " + index + " has been deleted");
    }

    /**
     * Displays the goodbye message.
     */
    public void showBye() {
        System.out.println("Bye bye! See you soon!");
    }

    /**
     * Displays the list of tasks that match the search query.
     *
     * @param matchedTasks The TaskList containing matched tasks.
     */
    public void showMatched(TaskList matchedTasks){
        System.out.println("Here are the matching tasks:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println((i + 1) + ":" + matchedTasks.getTasks().get(i));
        }
    }
}

package jongbot;

import java.util.Scanner;

import jongbot.exceptions.EmptyListException;
import jongbot.exceptions.JongExceptions;

import static jongbot.MyConstants.dashLine;
import static jongbot.MyConstants.newline;

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showWelcome() {
        newline();
        System.out.println(MyConstants.WELCOME_MESSAGE);
        newline();
    }

    public void showHelp() {
        System.out.println(MyConstants.HELP_MESSAGE);
    }

    public void showLine() {
        dashLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showList(TaskList tasks) throws JongExceptions {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        System.out.println("Here's your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ":" + tasks.getTasks().get(i));
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Adding this task to the list:");
        System.out.println(task);
        System.out.println("Number of tasks in list: " + size);
    }

    public void showMarked(int index) {
        System.out.println("Task " + index + " has been marked");
    }

    public void showUnmarked(int index) {
        System.out.println("Task " + index + " has been unmarked");
    }

    public void showDeleted(int index) {
        System.out.println("Task " + index + " has been deleted");
    }

    public void showBye() {
        System.out.println("Bye bye! See you soon!");
    }
}

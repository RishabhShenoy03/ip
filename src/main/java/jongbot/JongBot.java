package jongbot;

import jongbot.exceptions.JongExceptions;

public class JongBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a JongBot instance with the specified tasks file path.
     * Initializes the UI, storage, parser, and loads existing tasks from file.
     *
     * @param tasksFilePath The path to the file where tasks are stored.
     */
    public JongBot(String tasksFilePath) {
        ui = new Ui();
        storage = new Storage(tasksFilePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (JongExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot.
     * Continuously reads user commands, processes them, and displays responses.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();

            try {
                boolean shouldExit = parser.execute(input, tasks, storage, ui);
                if (shouldExit) {
                    return;
                }
            } catch (JongExceptions e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Invalid task index. use \"list\" to see all tasks");
            }
            ui.showLine();
            MyConstants.newline();
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new JongBot("data/tasks.txt").run();
    }
}

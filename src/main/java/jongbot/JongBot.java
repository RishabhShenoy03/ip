package jongbot;

import jongbot.exceptions.JongExceptions;

public class JongBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a JongBot instance backed by the given file path.
     *
     * @param tasksFilePath Path to the tasks file.
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
     * Runs the main input-processing loop.
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
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new JongBot("data/tasks.txt").run();
    }
}

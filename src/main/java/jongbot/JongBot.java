package jongbot;

import jongbot.exceptions.JongExceptions;

public class JongBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public static void main(String[] args) {
        new JongBot("data/tasks.txt").run();
    }
}

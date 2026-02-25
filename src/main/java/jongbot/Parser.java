package jongbot;

import jongbot.exceptions.EmptyDeadlineDescriptionException;
import jongbot.exceptions.EmptyEventDescriptionException;
import jongbot.exceptions.EmptyTodoException;
import jongbot.exceptions.JongExceptions;
import jongbot.exceptions.MissingDeadlineException;
import jongbot.exceptions.MissingEventTimeException;
import jongbot.exceptions.NotAnyException;

import java.util.ArrayList;

public class Parser {

    /**
     * Parses the input string into a command and its arguments.
     *
     * @param input The raw input string from the user.
     * @return A ParsedCommand object containing the command and arguments.
     * @throws JongExceptions If the input is empty or invalid.
     */
    public ParsedCommand parseCommand(String input) throws JongExceptions {
        String trimmed = input == null ? "" : input.trim();
        if (trimmed.isEmpty()) {
            throw new NotAnyException();
        }

        int firstSpace = trimmed.indexOf(' ');
        if (firstSpace == -1) {
            return new ParsedCommand(trimmed, "");
        }

        String command = trimmed.substring(0, firstSpace);
        String arguments = trimmed.substring(firstSpace + 1).trim();
        return new ParsedCommand(command, arguments);
    }

    /**
     * Executes the parsed command and performs the corresponding action.
     *
     * @param input The raw input string from the user.
     * @param tasks The TaskList to operate on.
     * @param storage The Storage object for saving tasks.
     * @param ui The UI object for displaying messages.
     * @return true if the program should exit, false otherwise.
     * @throws JongExceptions If any error occurs during command execution.
     */
    public boolean execute(String input, TaskList tasks, Storage storage, Ui ui) throws JongExceptions {
        ParsedCommand parsed = parseCommand(input);
        switch (parsed.command) {
        case "todo":
            Task todo = parseTodo(parsed.arguments);
            tasks.add(todo);
            storage.save(tasks);
            ui.showTaskAdded(todo, tasks.size());
            return false;
        case "deadline":
            Task deadline = parseDeadline(parsed.arguments);
            tasks.add(deadline);
            storage.save(tasks);
            ui.showTaskAdded(deadline, tasks.size());
            return false;
        case "event":
            Task event = parseEvent(parsed.arguments);
            tasks.add(event);
            storage.save(tasks);
            ui.showTaskAdded(event, tasks.size());
            return false;
        case "bye":
            ui.showBye();
            return true;
        case "list":
            ui.showList(tasks);
            return false;
        case "mark":
            tasks.mark(parsed.arguments);
            storage.save(tasks);
            ui.showMarked(Integer.parseInt(parsed.arguments.trim()));
            return false;
        case "unmark":
            tasks.unmark(parsed.arguments);
            storage.save(tasks);
            ui.showUnmarked(Integer.parseInt(parsed.arguments.trim()));
            return false;
        case "delete":
            tasks.delete(parsed.arguments);
            storage.save(tasks);
            ui.showDeleted(Integer.parseInt(parsed.arguments.trim()));
            return false;
        case "help":
            ui.showHelp();
            return false;
        case "find":
            TaskList matchedTasks = tasks.findMatches(parsed.arguments);
            ui.showMatched(matchedTasks);
            return false;
        default:
            throw new jongbot.exceptions.NotAnyException();
        }
    }

    /**
     * Parses the arguments for a todo command and creates a Todo task.
     *
     * @param arguments The description of the todo task.
     * @return A new Todo task.
     * @throws JongExceptions If the arguments are blank.
     */
    public Task parseTodo(String arguments) throws JongExceptions {
        if (arguments.isBlank()) {
            throw new EmptyTodoException();
        }
        return new Todo(arguments);
    }

    /**
     * Parses the arguments for a deadline command and creates a Deadline task.
     *
     * @param arguments The description and deadline in the format "description /by deadline".
     * @return A new Deadline task.
     * @throws JongExceptions If the arguments are blank or missing the /by keyword.
     */
    public Task parseDeadline(String arguments) throws JongExceptions {
        int byIndex = arguments.indexOf("/by");
        if (arguments.isBlank()) {
            throw new EmptyDeadlineDescriptionException();
        }
        if (byIndex == -1) {
            throw new MissingDeadlineException();
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 3).trim();
        return new Deadline(description, by);
    }

    /**
     * Parses the arguments for an event command and creates an Event task.
     *
     * @param arguments The description and time period in the format "description /from start /to end".
     * @return A new Event task.
     * @throws JongExceptions If the arguments are blank or missing /from and /to keywords.
     */
    public Task parseEvent(String arguments) throws JongExceptions {
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
        return new Event(description, from, to);
    }

    /**
     * A simple data class to hold the parsed command and its arguments.
     */
    public static class ParsedCommand {
        public final String command;
        public final String arguments;

        /**
         * Constructs a ParsedCommand with the given command and arguments.
         *
         * @param command The command string.
         * @param arguments The arguments string.
         */
        public ParsedCommand(String command, String arguments) {
            this.command = command;
            this.arguments = arguments;
        }
    }
}

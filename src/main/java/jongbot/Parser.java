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

    public Task parseTodo(String arguments) throws JongExceptions {
        if (arguments.isBlank()) {
            throw new EmptyTodoException();
        }
        return new Todo(arguments);
    }

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

    public static class ParsedCommand {
        public final String command;
        public final String arguments;

        public ParsedCommand(String command, String arguments) {
            this.command = command;
            this.arguments = arguments;
        }
    }
}

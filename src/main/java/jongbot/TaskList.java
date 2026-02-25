package jongbot;

import java.util.ArrayList;

import jongbot.exceptions.TaskIndexException;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list backed by the provided tasks.
     *
     * @param tasks Initial tasks; if null, an empty list is used.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the list is empty.
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task by its 1-based index string.
     *
     * @param indexString 1-based index as a string.
     * @throws TaskIndexException If the index is invalid.
     */
    public void delete(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        tasks.remove(index - 1);
    }

    /**
     * Marks a task done by its 1-based index string.
     *
     * @param indexString 1-based index as a string.
     * @throws TaskIndexException If the index is invalid.
     */
    public void mark(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        Task task = tasks.get(index - 1);
        task.markTask();
    }

    /**
     * Marks a task not done by its 1-based index string.
     *
     * @param indexString 1-based index as a string.* @throws TaskIndexException If the index is invalid.
     */
    public void unmark(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        Task task = tasks.get(index - 1);
        task.unmarkTask();
    }

    private int parseIndex(String indexString) throws TaskIndexException {
        try {
            int index = Integer.parseInt(indexString.trim());
            if (index < 1 || index > tasks.size()) {
                throw new TaskIndexException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }
}

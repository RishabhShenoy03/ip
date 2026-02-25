package jongbot;

import java.util.ArrayList;

import jongbot.exceptions.TaskIndexException;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param indexString The string representation of the task index (1-based).
     * @throws TaskIndexException If the index is invalid.
     */
    public void delete(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        tasks.remove(index - 1);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param indexString The string representation of the task index (1-based).
     * @throws TaskIndexException If the index is invalid.
     */
    public void mark(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        Task task = tasks.get(index - 1);
        task.markTask();
    }

    /**
     * Unmarks a task at the specified index.
     *
     * @param indexString The string representation of the task index (1-based).
     * @throws TaskIndexException If the index is invalid.
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

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     * The search is case-insensitive and matches whole words only.
     *
     * @param arguments The keyword to search for.
     * @return A new TaskList containing all matching tasks.
     */
    public TaskList findMatches(String arguments){
        TaskList matchedTasks = new TaskList();
        for (Task task : tasks) {
            // pad space on both sides to find word itself (not part of other words)
            String description = " " + task.description.toLowerCase() + " ";
            if (description.contains(" " + arguments + " ")){
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}

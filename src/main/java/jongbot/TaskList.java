package jongbot;

import java.util.ArrayList;

import jongbot.exceptions.TaskIndexException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        return tasks.remove(index - 1);
    }

    public Task mark(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        Task task = tasks.get(index - 1);
        task.markTask();
        return task;
    }

    public Task unmark(String indexString) throws TaskIndexException {
        int index = parseIndex(indexString);
        Task task = tasks.get(index - 1);
        task.unmarkTask();
        return task;
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

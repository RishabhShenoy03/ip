package jongbot;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description, initially not done.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the display string for this task.
     *
     * @return Display string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the serialized form for storage.
     *
     * @return Storage string.
     */
    public String toDataString() {
        return description;
    }
}


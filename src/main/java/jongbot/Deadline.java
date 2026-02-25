package jongbot;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
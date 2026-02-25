package jongbot;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description Task description.
     * @param by          Due date/time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the display string for this deadline.
     *
     * @return Display string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the serialized form for storage.
     *
     * @return Storage string.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}

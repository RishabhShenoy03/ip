package jongbot;

public class Todo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the display string for this todo.
     *
     * @return Display string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the serialized form for storage.
     *
     * @return Storage string.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}

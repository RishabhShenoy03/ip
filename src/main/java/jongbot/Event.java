package jongbot;

public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Creates an event task with the given description and time range.
     *
     * @param description Task description.
     * @param from        Start time.
     * @param to          End time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the display string for this event.
     *
     * @return Display string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the serialized form for storage.
     *
     * @return Storage string.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | "+ description + " | " + from + " | " + to;
    }
}

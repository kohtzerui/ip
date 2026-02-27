/**
 * Represents a Todo task.
 * A Todo is a task with only a description and no date/time attached.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * Format: {@code T | <isDone> | <description>}.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    /**
     * {@inheritDoc}
     * Format: {@code [T][status] description}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

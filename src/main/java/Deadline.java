import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 * A Deadline has a description and a due date specified by the user.
 * If the due date is in ISO-8601 format (e.g. "2025-12-31"), it will be
 * parsed and displayed in a more readable format (e.g. "Dec 31 2025").
 */
public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected String by;
    protected LocalDate byDate;

    /**
     * Constructs a new Deadline with the given description and due date string.
     * Attempts to parse the due date as a {@link LocalDate} in ISO-8601 format.
     *
     * @param description The description of the deadline task.
     * @param by          The due date string (e.g. "2025-12-31" or free-form text).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    /**
     * Returns the raw due date string as provided by the user.
     *
     * @return The due date string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the parsed due date, or {@code null} if parsing failed.
     *
     * @return The due date as a {@link LocalDate}, or {@code null}.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * {@inheritDoc}
     * Format: {@code D | <isDone> | <description> | <by>}.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by;
    }

    /**
     * {@inheritDoc}
     * Displays the due date in "MMM d yyyy" format if it was successfully parsed,
     * otherwise displays the raw string.
     */
    @Override
    public String toString() {
        String displayBy = (byDate != null) ? byDate.format(OUTPUT_FORMAT) : by;
        return "[D]" + super.toString() + " (by: " + displayBy + ")";
    }
}

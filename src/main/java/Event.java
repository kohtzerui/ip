import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task.
 * An Event has a description, a start date/time, and an end date/time.
 * If the dates are in ISO-8601 format (e.g. "2025-12-31"), they will be
 * parsed and displayed in a more readable format (e.g. "Dec 31 2025").
 */
public class Event extends Task {

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs a new Event with the given description, start, and end date
     * strings.
     * Attempts to parse both dates as {@link LocalDate} in ISO-8601 format.
     *
     * @param description The description of the event.
     * @param from        The start date/time string.
     * @param to          The end date/time string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    /**
     * Returns the raw start date/time string as provided by the user.
     *
     * @return The start date/time string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the raw end date/time string as provided by the user.
     *
     * @return The end date/time string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the parsed start date, or {@code null} if parsing failed.
     *
     * @return The start date as a {@link LocalDate}, or {@code null}.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the parsed end date, or {@code null} if parsing failed.
     *
     * @return The end date as a {@link LocalDate}, or {@code null}.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * {@inheritDoc}
     * Format: {@code E | <isDone> | <description> | <from> | <to>}.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

    /**
     * {@inheritDoc}
     * Displays dates in "MMM d yyyy" format if they were successfully parsed,
     * otherwise displays the raw strings.
     */
    @Override
    public String toString() {
        String displayFrom = (fromDate != null) ? fromDate.format(OUTPUT_FORMAT) : from;
        String displayTo = (toDate != null) ? toDate.format(OUTPUT_FORMAT) : to;
        return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
    }
}

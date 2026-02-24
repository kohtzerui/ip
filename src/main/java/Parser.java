public class Parser {
    public static Command parse(String fullCommand) throws NimbusException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseIndex(arguments));
            case "unmark":
                return new UnmarkCommand(parseIndex(arguments));
            case "delete":
                return new DeleteCommand(parseIndex(arguments));
            case "todo":
                return parseTodo(arguments);
            case "deadline":
                return parseDeadline(arguments);
            case "event":
                return parseEvent(arguments);
            default:
                throw new NimbusException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String arguments) throws NimbusException {
        if (arguments.isEmpty()) {
            throw new NimbusException("The task number cannot be empty.");
        }
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new NimbusException("The task number must be an integer.");
        }
    }

    private static Command parseTodo(String arguments) throws NimbusException {
        if (arguments.isEmpty()) {
            throw new NimbusException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(arguments));
    }

    private static Command parseDeadline(String arguments) throws NimbusException {
        if (arguments.isEmpty()) {
            throw new NimbusException("The description of a deadline cannot be empty.");
        }
        int byIndex = arguments.indexOf("/by");
        if (byIndex == -1) {
            throw new NimbusException("Please specify /by [date/time].");
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 3).trim();
        if (description.isEmpty()) {
            throw new NimbusException("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new NimbusException("The date of a deadline cannot be empty.");
        }
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEvent(String arguments) throws NimbusException {
        if (arguments.isEmpty()) {
            throw new NimbusException("The description of an event cannot be empty.");
        }
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new NimbusException("Please specify /from [start] and /to [end].");
        }
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new NimbusException("The description of an event cannot be empty.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new NimbusException("The start and end time of an event cannot be empty.");
        }
        return new AddCommand(new Event(description, from, to));
    }
}

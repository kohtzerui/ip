import java.util.Scanner;

public class Nimbus {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printWelcome();
        Scanner scanner = new Scanner(System.in);
        runCommandLoop(scanner);
        scanner.close();
    }

    private static void runCommandLoop(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);

            try {
                if (input.equals("bye")) {
                    printGoodbye();
                    break;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else {
                    throw new NimbusException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NimbusException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }

    private static void printWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Nimbus");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
    }

    private static void markTask(String input) throws NimbusException {
        try {
            if (input.equals("mark")) {
                throw new NimbusException(" OOPS!!! The task number for mark cannot be empty.");
            }
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 0 || index >= taskCount) {
                throw new NimbusException(" OOPS!!! The task number is invalid.");
            }
            tasks[index].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[index]);
        } catch (NumberFormatException e) {
            throw new NimbusException(" OOPS!!! The task number must be an integer.");
        }
    }

    private static void unmarkTask(String input) throws NimbusException {
        try {
            if (input.equals("unmark")) {
                throw new NimbusException(" OOPS!!! The task number for unmark cannot be empty.");
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= taskCount) {
                throw new NimbusException(" OOPS!!! The task number is invalid.");
            }
            tasks[index].markAsUndone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[index]);
        } catch (NumberFormatException e) {
            throw new NimbusException(" OOPS!!! The task number must be an integer.");
        }
    }

    private static void addTodo(String input) throws NimbusException {
        if (input.equals("todo")) {
            throw new NimbusException(" OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new NimbusException(" OOPS!!! The description of a todo cannot be empty.");
        }
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void addDeadline(String input) throws NimbusException {
        if (input.equals("deadline")) {
            throw new NimbusException(" OOPS!!! The description of a deadline cannot be empty.");
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new NimbusException(" OOPS!!! The deadline cannot be empty. Please use /by to specify the date.");
        }
        String description = input.substring(9, byIndex).trim();
        if (description.isEmpty()) {
            throw new NimbusException(" OOPS!!! The description of a deadline cannot be empty.");
        }
        String by = input.substring(byIndex + 3).trim();
        if (by.isEmpty()) {
            throw new NimbusException(" OOPS!!! The date of a deadline cannot be empty.");
        }
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void addEvent(String input) throws NimbusException {
        if (input.equals("event")) {
            throw new NimbusException(" OOPS!!! The description of an event cannot be empty.");
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new NimbusException(
                    " OOPS!!! The event time cannot be empty. Please use /from and /to to specify the time.");
        }
        String description = input.substring(6, fromIndex).trim();
        if (description.isEmpty()) {
            throw new NimbusException(" OOPS!!! The description of an event cannot be empty.");
        }
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        if (from.isEmpty() || to.isEmpty()) {
            throw new NimbusException(" OOPS!!! The start and end time of an event cannot be empty.");
        }
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void printTaskAdded(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }
}

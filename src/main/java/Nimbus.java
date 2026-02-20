import java.util.ArrayList;
import java.util.Scanner;

public class Nimbus {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static ArrayList<Task> tasks = new ArrayList<>();

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

            if (input.equals("bye")) {
                printGoodbye();
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo ")) {
                addTodo(input);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input);
            } else if (input.startsWith("event ")) {
                addEvent(input);
            } else if (input.startsWith("delete ")) {
                deleteTask(input);
            } else {
                System.out.println(" Unknown command.");
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
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            tasks.get(index).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(" Invalid task number.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            tasks.get(index).markAsUndone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(" Invalid task number.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTodo(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println(" Description cannot be empty.");
            System.out.println(HORIZONTAL_LINE);
            return;
        }
        Task task = new Todo(description);
        tasks.add(task);
        printTaskAdded(task);
    }

    private static void addDeadline(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            System.out.println(" Please specify /by [date/time].");
            System.out.println(HORIZONTAL_LINE);
            return;
        }
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAdded(task);
    }

    private static void addEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println(" Please specify /from [start] and /to [end].");
            System.out.println(HORIZONTAL_LINE);
            return;
        }
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAdded(task);
    }

    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println(" Invalid task number.");
                System.out.println(HORIZONTAL_LINE);
                return;
            }
            Task removed = tasks.remove(index);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removed);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println(" Invalid task number.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printTaskAdded(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
}

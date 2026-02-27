import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface interactions.
 * This class is responsible for reading user input and displaying messages.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui that reads from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal divider line.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Nimbus");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays a warning message when the data file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println(" Warning: Could not load data file. Starting with empty task list.");
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a confirmation message after a task is removed.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after removal.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a confirmation message after a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Displays a confirmation message after a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The task list to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }
}

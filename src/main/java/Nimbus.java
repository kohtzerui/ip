import java.util.Scanner;

public class Nimbus {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Nimbus");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[index]);
                System.out.println(HORIZONTAL_LINE);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[index]);
                System.out.println(HORIZONTAL_LINE);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(" added: " + input);
                System.out.println(HORIZONTAL_LINE);
            }
        }
        scanner.close();
    }
}

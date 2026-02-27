import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 * The file format uses pipe-delimited fields to represent each task.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage that reads from and writes to the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     * If the file does not exist, returns an empty list.
     * Corrupted lines are skipped with a warning message.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws NimbusException If the file cannot be read.
     */
    public ArrayList<Task> load() throws NimbusException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task task = parseLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.out.println(" Warning: Skipping corrupted line: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new NimbusException("Could not load data file.");
        }
        return tasks;
    }

    /**
     * Parses a single line from the data file and returns the corresponding Task.
     * The expected format is pipe-delimited:
     * {@code <type> | <isDone> | <description> [| ...]}.
     *
     * @param line The line to parse.
     * @return The parsed Task, or {@code null} if the line format is invalid.
     */
    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                task = new Deadline(description, parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                task = new Event(description, parts[3].trim(), parts[4].trim());
                break;
            default:
                return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the given list of tasks to the data file.
     * Creates the parent directories if they do not exist.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(" Warning: Could not save data file.");
        }
    }
}

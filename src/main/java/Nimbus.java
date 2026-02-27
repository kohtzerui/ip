/**
 * The main class of the Nimbus task management chatbot.
 * Nimbus allows users to manage tasks (todos, deadlines, events) via a
 * command-line interface, with persistent storage to a local file.
 */
public class Nimbus {
    private static final String DATA_FILE_PATH = "." + java.io.File.separator + "data"
            + java.io.File.separator + "nimbus.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Nimbus application instance.
     * Initialises the UI, storage, and loads existing tasks from the file.
     * If the file is corrupted or missing, starts with an empty task list.
     *
     * @param filePath The path to the data file for persistent storage.
     */
    public Nimbus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NimbusException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop of the application.
     * Continuously reads user commands, parses and executes them
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NimbusException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Nimbus application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Nimbus(DATA_FILE_PATH).run();
    }
}

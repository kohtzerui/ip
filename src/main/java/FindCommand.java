import java.util.ArrayList;

/**
 * Represents a command to find tasks matching a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks whose descriptions
     * contain the keyword and displaying the matching results.
     *
     * @param tasks   The task list to search through.
     * @param ui      The UI to display matching tasks.
     * @param storage The storage (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                matchingTasks.add(tasks.getTask(i));
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }
}

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI to display the task list.
     * @param storage The storage (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

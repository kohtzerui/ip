/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list,
     * displaying a confirmation message, and saving the updated list.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The UI to display the confirmation.
     * @param storage The storage to persist the updated task list.
     * @throws NimbusException If an error occurs while saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NimbusException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}

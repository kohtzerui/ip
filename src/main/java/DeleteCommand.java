/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The zero-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the task at the specified index,
     * displaying a confirmation message, and saving the updated list.
     *
     * @param tasks   The task list to delete the task from.
     * @param ui      The UI to display the confirmation.
     * @param storage The storage to persist the updated task list.
     * @throws NimbusException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NimbusException {
        if (index < 0 || index >= tasks.size()) {
            throw new NimbusException("Invalid task number.");
        }
        Task removed = tasks.deleteTask(index);
        ui.showTaskRemoved(removed, tasks.size());
        storage.save(tasks.getTasks());
    }
}

/**
 * Represents a command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as not done,
     * displaying a confirmation message, and saving the updated list.
     *
     * @param tasks   The task list containing the task to unmark.
     * @param ui      The UI to display the confirmation.
     * @param storage The storage to persist the updated task list.
     * @throws NimbusException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NimbusException {
        if (index < 0 || index >= tasks.size()) {
            throw new NimbusException("Invalid task number.");
        }
        tasks.getTask(index).markAsUndone();
        ui.showTaskUnmarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}

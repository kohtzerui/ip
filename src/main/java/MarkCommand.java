/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The zero-based index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as done,
     * displaying a confirmation message, and saving the updated list.
     *
     * @param tasks   The task list containing the task to mark.
     * @param ui      The UI to display the confirmation.
     * @param storage The storage to persist the updated task list.
     * @throws NimbusException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NimbusException {
        if (index < 0 || index >= tasks.size()) {
            throw new NimbusException("Invalid task number.");
        }
        tasks.getTask(index).markAsDone();
        ui.showTaskMarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}

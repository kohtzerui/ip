public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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

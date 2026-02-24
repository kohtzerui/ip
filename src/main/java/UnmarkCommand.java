public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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

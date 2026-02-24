public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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

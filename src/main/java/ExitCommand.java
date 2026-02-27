/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying a goodbye message.
     *
     * @param tasks   The task list (unused).
     * @param ui      The UI to display the goodbye message.
     * @param storage The storage (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns {@code true} to signal the application to exit.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

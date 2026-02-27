/**
 * Represents an abstract command that can be executed by the Nimbus
 * application.
 * Each command operates on a {@link TaskList}, interacts with the user through
 * {@link Ui},
 * and persists changes via {@link Storage}.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save/load tasks.
     * @throws NimbusException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NimbusException;

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return {@code true} if this is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

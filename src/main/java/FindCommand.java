import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                matchingTasks.add(tasks.getTask(i));
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }
}

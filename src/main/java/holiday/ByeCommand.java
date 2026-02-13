package holiday;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasksToFile(tasks.getTasks());
        return ui.getGoodbyeMessage();
    }

}

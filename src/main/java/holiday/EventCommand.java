package holiday;

public class EventCommand extends Command {

    private Events task;

    public EventCommand(Events task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        return ui.getAddTaskMessage(taskList.getTasks(), this.task);
    }
}

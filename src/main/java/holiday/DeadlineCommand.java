package holiday;

public class DeadlineCommand extends Command {
    private Deadlines task;

    public DeadlineCommand(Deadlines task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        return ui.getAddTaskMessage(taskList.getTasks(), this.task);
    }
}

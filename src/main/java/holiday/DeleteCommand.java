package holiday;

public class DeleteCommand extends Command{
    private int taskIdx;

    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.remove(taskIdx);
        return ui.getDeleteTaskMessage(tasks.getTasks(), removedTask);
    }
}

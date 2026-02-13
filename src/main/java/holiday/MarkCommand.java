package holiday;

public class MarkCommand extends Command {

    private int taskIdx;

    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task markedTask = Task.mark(taskList.getTasks(), taskIdx);
        return ui.getMarkMessage(taskList.getTasks(), taskIdx);
    }
}

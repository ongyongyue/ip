package holiday;

public class UnmarkCommand extends Command{

    private int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task unmarkedTask = Task.unmark(taskList.getTasks(), taskIdx);
        return ui.getUnmarkMessage(taskList.getTasks(), taskIdx);
    }
}

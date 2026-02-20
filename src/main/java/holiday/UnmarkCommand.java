package holiday;

public class UnmarkCommand extends Command{

    private int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException{
        if (taskList.size() == 0) {
            throw new HolidayException("List is empty, nothing to unmark!");
        } else if (taskIdx > taskList.size()) {
            throw new HolidayException("Index does not exist");
        }
        Task unmarkedTask = Task.unmark(taskList.getTasks(), taskIdx);
        return ui.getUnmarkMessage(taskList.getTasks(), taskIdx);
    }
}

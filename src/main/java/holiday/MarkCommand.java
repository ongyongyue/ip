package holiday;

/**
 * Command that marks a task as completed.
 */
public class MarkCommand extends Command {

    private int taskIdx;

    /**
     * Creates a command to mark a task.
     *
     * @param taskIdx index of task to mark
     */
    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Marks the task as done and returns confirmation.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return confirmation message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException {
        if (taskList.size() == 0) {
            throw new HolidayException("List is empty, nothing to mark!");
        } else if (taskIdx > taskList.size()) {
            throw new HolidayException("Index does not exist");
        }
        Task markedTask = Task.mark(taskList.getTasks(), taskIdx);
        return ui.getMarkMessage(taskList.getTasks(), taskIdx);
    }
}
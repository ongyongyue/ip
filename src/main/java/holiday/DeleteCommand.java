package holiday;

/**
 * Command that removes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIdx;

    /**
     * Creates a command to delete a task.
     *
     * @param taskIdx index of task to remove
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Removes the specified task and returns confirmation.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return confirmation message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException {
        if (taskList.size() == 0) {
            throw new HolidayException("List is empty, nothing to delete!");
        } else if (taskIdx > taskList.size()) {
            throw new HolidayException("Index does not exist");
        }
        Task removedTask = taskList.remove(taskIdx);
        return ui.getDeleteTaskMessage(taskList.getTasks(), removedTask);
    }
}
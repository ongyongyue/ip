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
     * @param tasks current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return confirmation message
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HolidayException {
        if (tasks.size() == 0) {
            throw new HolidayException("List is empty, nothing to delete!");
        } else if (taskIdx > tasks.size()) {
            throw new HolidayException("Index does not exist");
        }
        Task removedTask = tasks.remove(taskIdx);
        return ui.getDeleteTaskMessage(tasks.getTasks(), removedTask);
    }
}
package holiday;

/**
 * Command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private Deadlines task;

    /**
     * Creates a command to add a deadline task.
     *
     * @param task deadline task to be added
     */
    public DeadlineCommand(Deadlines task) {
        this.task = task;
    }

    /**
     * Adds the deadline task and returns confirmation message.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return confirmation message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        return ui.getAddTaskMessage(taskList.getTasks(), this.task);
    }
}
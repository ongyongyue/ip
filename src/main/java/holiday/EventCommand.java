package holiday;

/**
 * Command that adds an event task to the task list.
 */
public class EventCommand extends Command {

    private Events task;

    /**
     * Creates a command to add an event task.
     *
     * @param task event task to add
     */
    public EventCommand(Events task) {
        this.task = task;
    }

    /**
     * Adds the event task and returns confirmation.
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
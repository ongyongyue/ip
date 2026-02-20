package holiday;

/**
 * Command that displays all tasks in the list.
 */
public class ListCommand extends Command {

    /**
     * Returns a formatted list of tasks.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return formatted task list message
     * @throws HolidayException if the list is empty
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException {
        return ui.getListMessage(taskList.getTasks());
    }
}
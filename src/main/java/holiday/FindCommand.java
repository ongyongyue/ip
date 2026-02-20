package holiday;

/**
 * Command that searches for tasks containing a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a find command.
     *
     * @param keyword keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds matching tasks and returns results.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return formatted matching tasks message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getMatchingTasksMessage(taskList.find(keyword));
    }
}
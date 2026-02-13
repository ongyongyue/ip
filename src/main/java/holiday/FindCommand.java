package holiday;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getMatchingTasksMessage(taskList.find(keyword));
    }
}

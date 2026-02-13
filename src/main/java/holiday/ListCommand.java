package holiday;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage){
        return ui.getListMessage(taskList.getTasks());
    }
}

package holiday;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException{
        return ui.getListMessage(taskList.getTasks());
    }
}

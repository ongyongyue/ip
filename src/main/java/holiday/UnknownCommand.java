package holiday;

public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getErrorMessage("Unknown Command!");
    }
}

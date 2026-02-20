package holiday;

/**
 * Represents an unknown or unsupported command entered by the user.
 * Returns an error message indicating the command is invalid.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command response.
     *
     * @param taskList current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return error message indicating unknown command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getErrorMessage("Unknown Command!");
    }
}
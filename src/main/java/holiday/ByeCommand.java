package holiday;

import java.io.IOException;

/**
 * Command that saves tasks to file and exits the application.
 */
public class ByeCommand extends Command {

    /**
     * Saves tasks and returns the goodbye message.
     *
     * @param tasks current task list
     * @param ui user interface handler
     * @param storage storage handler
     * @return goodbye message for display
     * @throws IOException if saving tasks fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTasksToFile(tasks.getTasks());
        return ui.getGoodbyeMessage();
    }
}
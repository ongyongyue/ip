package holiday;

import java.io.IOException;

/**
 * Represents an executable command in the Holiday chatbot.
 * All concrete commands must implement the execute method.
 */
abstract public class Command {

    /**
     * Executes the command.
     *
     * @param tasks current task list
     * @param ui user interface handler
     * @param storage storage handler for saving/loading tasks
     * @return message to be displayed to the user
     * @throws HolidayException if command execution fails
     * @throws IOException if a storage operation fails
     */
    abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws HolidayException, IOException;
}
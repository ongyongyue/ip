package holiday;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTasksToFile(tasks.getTasks());
        return ui.getGoodbyeMessage();
    }

}

package holiday.Command;

import holiday.Storage;
import holiday.TaskList;
import holiday.Ui;

abstract public class Command {
    abstract String execute(TaskList tasks, Ui ui, Storage storage);
}

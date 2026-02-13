package holiday;

import java.io.IOException;

abstract public class Command {
    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws HolidayException, IOException;
}

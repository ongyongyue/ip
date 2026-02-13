package holiday;

abstract public class Command {
    abstract String execute(TaskList tasks, Ui ui, Storage storage);
}

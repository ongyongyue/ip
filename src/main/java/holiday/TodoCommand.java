package holiday;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDos task = new ToDos(description);
        tasks.add(task);
        return ui.getAddTaskMessage(tasks.getTasks(), task);
    }
}


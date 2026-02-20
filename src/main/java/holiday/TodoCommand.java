package holiday;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws HolidayException {
        ToDos task = new ToDos(description);
        if (taskList.contains(task)) {
            throw new HolidayException("This task already exists!");
        }
        taskList.add(task);
        return ui.getAddTaskMessage(taskList.getTasks(), task);
    }
}


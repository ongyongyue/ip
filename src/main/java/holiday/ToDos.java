package holiday;

/**
 * Represents a simple to-do task without any date or time.
 * A ToDos task contains only a description and completion status.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the given description.
     *
     * @param description description of the task
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return formatted task string with ToDos prefix
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the file storage representation of the task.
     *
     * @return string formatted for saving to file
     */
    @Override
    public String toFileString() {
        return String.format("T,%s", this.description);
    }
}
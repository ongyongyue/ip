package holiday;

import java.util.List;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with a description.
     *
     * @param description task description
     */
    Task(String description) {
        assert description != null : "Description should not be null";
        assert !description.isBlank() : "Description should not be blank";
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public void setDone() {
        this.isDone = true;
    }

    /** Marks the task as not done. */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns completion status.
     *
     * @return true if done, false otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks a task as completed.
     *
     * @param iList task list
     * @param index index of task to mark
     * @return marked task
     */
    public static Task mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        return currentTask;
    }

    /**
     * Marks a task as not completed.
     *
     * @param iList task list
     * @param index index of task to unmark
     * @return updated task
     */
    public static Task unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        return currentTask;
    }

    /**
     * Returns display format of the task.
     *
     * @return formatted task string
     */
    public String toString() {
        return String.format(
                "%s %s",
                getIsDone() ? "[X]" : "[ ]",
                this.description
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task other = (Task) obj;
        return this.description.equalsIgnoreCase(other.description);
    }

    /**
     * Converts the task into file storage format.
     *
     * @return string representation for saving
     */
    public String toFileString() {
        return String.format("%s", description);
    }
}
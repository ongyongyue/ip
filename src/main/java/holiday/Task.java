package holiday;

import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        assert description != null : "Description should not be null";
        assert !description.isBlank() : "Description should not be blank";
        this.description = description;
        this.isDone = false;
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public static Task mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        System.out.println("\t--------------------------------------------\n"
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
        return currentTask;
    }

    /*
    Mark the indexed task in the TaskList iList as not done
     */
    public static Task unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        System.out.println("\t--------------------------------------------\n"
                + "\t OK, I've marked this task as not done yet:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
        return currentTask;
    }

    public String toString() {
        return String.format(
                "%s %s",
                getIsDone() ? "[X]" : "[ ]",
                this.description
        );
    }

    /*
    Function that returns information about Task to be stored in a txt file
    in a nice format
     */
    public String toFileString() {
        return String.format("%s", description);
    }

}

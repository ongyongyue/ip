
public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
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

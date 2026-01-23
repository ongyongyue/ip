
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

}

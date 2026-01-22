public class Item {
    private String name;
    private boolean isDone;

    Item(String name) {
        this.name = name;
        this.isDone = false;
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

}

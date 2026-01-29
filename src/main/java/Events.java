public class Events extends Task {
    private String start;
    private String end;
    Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String toFileString() {
        return String.format("E,%s,%s,%s", this.description, this.start, this.end);
    }

}

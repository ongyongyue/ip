public class Deadlines extends Task {
    private String doBy;
    Deadlines(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.doBy);
    }
}

public class ToDos extends Task{
    ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}

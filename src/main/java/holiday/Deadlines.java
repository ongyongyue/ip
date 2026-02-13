package holiday;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadlines extends Task {
    private LocalDateTime doByDate;

    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    Deadlines(String description, String doByString) {
        super(description);
        this.doByDate = LocalDateTime.parse(doByString.trim(), INPUT);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Deadlines other = (Deadlines) obj;
        return this.doByDate.equals(other.doByDate);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doByDate.format(OUTPUT));
    }

    @Override
    public String toFileString() {
        return String.format("D,%s,%s", this.description, this.doByDate.format(INPUT));
    }
}

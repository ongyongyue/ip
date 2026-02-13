package holiday;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    Events(String description, String start, String end) {
        super(description);
        this.startDateTime = LocalDateTime.parse(start.trim(), INPUT);
        this.endDateTime = LocalDateTime.parse(end.trim(), INPUT);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;

        Events other = (Events) obj;
        return this.startDateTime.equals(other.startDateTime)
                && this.endDateTime.equals(other.endDateTime);
    }


    @Override
    public String toString() {
        return
            String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                this.startDateTime.format(OUTPUT),
                this.endDateTime.format(OUTPUT)
            );
    }

    @Override
    public String toFileString() {
        return
            String.format(
                "E,%s,%s,%s",
                this.description,
                this.startDateTime.format(INPUT),
                this.endDateTime.format(INPUT)
            );
    }

}

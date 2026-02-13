package holiday;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

public class Holiday {

    private static final Path DATA_FILE = Paths.get("data", "holiday.txt");
    private static final Storage savedTasks = new Storage(DATA_FILE);

    private final Ui ui = new Ui();
    private TaskList lst;

    public Holiday() {
        try {
            lst = new TaskList(savedTasks.loadTasksFromFile());
        } catch (IOException e) {
            lst = new TaskList();
        }
    }

    /** Processes one user input and returns the reply string for the GUI. */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String uiMessage = command.execute(lst, ui, savedTasks);
            return uiMessage;
        } catch (HolidayException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.getFormatErrorMessage();
        } catch (Exception e) {
            return ui.getErrorMessage("Something went wrong: " + e.getMessage());
        }

    }

    /** Optional: keep CLI entrypoint if you still want it. */
    public static void main(String[] args) {
        // You can keep your CLI here if you want, but GUI will not use this.
        new Ui().getWelcomeMessage();
    }
}

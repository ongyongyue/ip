package holiday;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

/**
 * Main application class for the Holiday chatbot.
 * Handles initialization and command execution.
 */
public class Holiday {

    private static final Path DATA_FILE = Paths.get("data", "holiday.txt");
    private static final Storage savedTasks = new Storage(DATA_FILE);

    private final Ui ui = new Ui();
    private TaskList lst;

    /**
     * Creates the Holiday chatbot and loads saved tasks.
     */
    public Holiday() {
        try {
            lst = new TaskList(savedTasks.loadTasksFromFile());
        } catch (IOException e) {
            lst = new TaskList();
        }
    }

    /**
     * Processes one user input and returns the chatbot response.
     *
     * @param input user command string
     * @return response message to display
     */
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

    /**
     * Throws an exception if list is empty
     *
     */
    public void isEmptyList() throws HolidayException{
        if(this.lst.getTasks().isEmpty()) {
            throw new HolidayException("List is empty!!");
        }
    }
    /**
     * Optional CLI entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Ui().getWelcomeMessage();
    }
}
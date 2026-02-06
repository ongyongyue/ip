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
            String[] command = Parser.parseCommand(input);

            if (!Parser.isValidCommand(command[0])) {
                throw new HolidayException("Sorry, I don't recognise this command");
            }

            if (command[0].equalsIgnoreCase("bye")) {
                savedTasks.saveTasksToFile(lst.getTasks());
                return ui.getGoodbyeMessage(); // make Ui return a string, or just return "Bye!"
            } else if (command[0].equalsIgnoreCase("list")) {
                return ui.getListMessage(lst.getTasks());
            } else if (command[0].equalsIgnoreCase("mark")) {
                Ui.mark(lst.getTasks(), Integer.parseInt(command[1]));
                return ui.getMarkMessage(lst.getTasks(), Integer.parseInt(command[1]));
            } else if (command[0].equalsIgnoreCase("unmark")) {
                Ui.unmark(lst.getTasks(), Integer.parseInt(command[1]));
                return ui.getUnmarkMessage(lst.getTasks(), Integer.parseInt(command[1]));
            } else if (command[0].equalsIgnoreCase("todo")) {
                ToDos currentTask = new ToDos(command[1]);
                lst.add(currentTask);
                return ui.getAddTaskMessage(lst.getTasks(), currentTask);
            } else if (command[0].equalsIgnoreCase("deadline")) {
                String[] deadlineDetails = Parser.parseTimedEvent(command);
                Deadlines currentTask = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
                lst.add(currentTask);
                return ui.getAddTaskMessage(lst.getTasks(), currentTask);
            } else if (command[0].equalsIgnoreCase("event")) {
                String[] eventDetails = Parser.parseTimedEvent(command);
                Events currentTask = new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
                lst.add(currentTask);
                return ui.getAddTaskMessage(lst.getTasks(), currentTask);
            } else if (command[0].equalsIgnoreCase("delete")) {
                if (lst.size() == 0) throw new HolidayException("List is empty!!");
                if (command.length < 2) throw new HolidayException("Improper Command format");
                int idx = Integer.parseInt(command[1]);
                if (idx > lst.size() || idx <= 0) throw new HolidayException("Index doesn't exist, try again");
                Task removed = lst.remove(idx);
                return ui.getDeleteTaskMessage(lst.getTasks(), removed);
            } else if (command[0].equalsIgnoreCase("find")) {
                String keyword = Parser.parseFindKeyword(input);
                TaskList matches = new TaskList(lst.find(keyword));
                return ui.getMatchingTasksMessage(matches);
            } else {
                lst.add(new Task(input));
                return ui.getEchoMessage(input);
            }

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

package holiday;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Holiday {
    private static final Path DATA_FILE = Paths.get("data", "holiday.txt");
    private static Storage savedTasks = new Storage(DATA_FILE);

    public static void main(String[] args) throws HolidayException, IOException, DateTimeParseException {
        Ui ui = new Ui();
        ui.showWelcome();
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        TaskList lst = new TaskList(savedTasks.loadTasksFromFile());
        while(true) {
            message = ui.readCommand();
            String[] command = Parser.parseCommand(message);
            try {
                if (args == null) {
                    throw new HolidayException("No blank entries");
                } else if (!Parser.isValidCommand(command[0])) {
                    throw new HolidayException("Sorry, I don't recognise this command");
                }
                if (command[0].equalsIgnoreCase("bye")) {
                    break;
                } else if (command[0].equalsIgnoreCase("list")) {
                    Ui.listOut(lst.getTasks());
                } else if (command[0].equalsIgnoreCase("mark")) {
                    Ui.mark(lst.getTasks(), Integer.parseInt(command[1]));
                } else if (command[0].equalsIgnoreCase("unmark")) {
                    Ui.unmark(lst.getTasks(), Integer.parseInt(command[1]));
                } else if (command[0].equalsIgnoreCase("todo")) {
                    ToDos currentTask = new ToDos(command[1]);
                    lst.add(currentTask);
                    ui.addTaskMessage(lst.getTasks(), currentTask);
                } else if (command[0].equalsIgnoreCase("deadline")) {
                    String[] deadlineDetails = Parser.parseTimedEvent(command);
                    Deadlines currentTask = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
                    lst.add(currentTask);
                    ui.addTaskMessage(lst.getTasks(),currentTask);

                } else if (command[0].equalsIgnoreCase("event")) {
                    String[] eventDetails = Parser.parseTimedEvent(command);
                    Events currentTask = new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
                    lst.add(currentTask);
                    ui.addTaskMessage(lst.getTasks(), currentTask);

                } else if (command[0].equalsIgnoreCase("Delete")) {
                    if (lst.size() == 0) { throw new HolidayException("List is empty!!"); }
                    if (command.length < 2) { throw new HolidayException("Improper Command format"); }
                    if (Integer.parseInt(command[1]) > lst.size() || Integer.parseInt(command[1]) < 0) {
                        throw new HolidayException("Index doesn't exist, try again");
                    }
                    Ui.deleteTaskMessage(lst.getTasks(), lst.remove(Integer.parseInt(command[1])));

                } else if (command[0].equalsIgnoreCase("Find")){
                    String keyword = Parser.parseFindKeyword(message);

                } else {
                    lst.add(new Task(message));
                    ui.showMessage(message);
                }


            } catch (HolidayException e) {
            ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showFormatErrorMessage();
            }
        }
        try {
            savedTasks.saveTasksToFile(lst.getTasks());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } finally {
            ui.showGoodbye();
        }

    }








}

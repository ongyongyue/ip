import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;


public class Holiday {
    private static final Path DATA_FILE = Paths.get("data", "holiday.txt");
    public enum CommandType {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE;
    }
    public static void main(String[] args) throws HolidayException, IOException, DateTimeParseException {
        Ui ui = new Ui();
        ui.showWelcome();
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        List<Task> lst = loadTasksFromFile();
        while(true) {
            message = ui.readCommand();
            String[] command = message.split(" ", 2);
            try {
                if (args == null) {
                    throw new HolidayException("No blank entries");
                } else if (!isValidCommand(command[0])) {
                    throw new HolidayException("Sorry, I don't recognise this command");
                } else if (!command[0].toLowerCase().equals("bye")
                        && !command[0].toLowerCase().equals("list")
                        && !command[0].toLowerCase().equals("delete")
                        && command.length < 2) throw new HolidayException("Description can't be blank");
                    if (command[0].toLowerCase().equals("bye")) {
                        break;
                    } else if (command[0].toLowerCase().equals("list")) {
                        Ui.listOut(lst);
                    } else if (command[0].toLowerCase().equals("mark")) {
                        Ui.mark(lst, Integer.parseInt(command[1]));
                    } else if (command[0].toLowerCase().equals("unmark")) {
                        Ui.unmark(lst, Integer.parseInt(command[1]));
                    } else if (command[0].toLowerCase().equals("todo")) {
                        ToDos currentTask = new ToDos(command[1]);
                        lst.add(currentTask);
                        ui.addTaskMessage(lst, currentTask);
                    } else if (command[0].toLowerCase().equals("deadline")) {
                        String[] segment = command[1].split("/");
                        if (segment.length < 2) throw new HolidayException("Improper Command format");
                        String[] dueBy = segment[1].split(" ", 2);
                        Deadlines currentTask = new Deadlines(segment[0], dueBy[1]);
                        lst.add(currentTask);
                        ui.addTaskMessage(lst,currentTask);

                    } else if (command[0].toLowerCase().equals("event")) {
                        String[] segment = command[1].split("/");
                        if (segment.length < 2) throw new HolidayException("Improper Command format");
                        String[] start = segment[1].split(" ", 2);
                        String[] end = segment[2].split(" ", 2);

                        Events currentTask = new Events(segment[0], start[1], end[1]);
                        lst.add(currentTask);
                        ui.addTaskMessage(lst, currentTask);

                    } else if (command[0].toLowerCase().equals("delete")) {
                        if (lst.size() == 0) throw new HolidayException("List is empty!!");
                        if (command.length < 2) throw new HolidayException("Improper Command format");
                        Ui.delete(lst, Integer.parseInt(command[1]));

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
            saveTasksToFile(lst);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } finally {
            ui.showGoodbye();
        }

    }

    public static boolean isValidCommand(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.name().equalsIgnoreCase(input)) {
            return true;
            }
        }
        return false;
    }

    /*
    Check if the data file exits, if it doesn't create the file and data directory
     */
    private static void ensureDataFileExists() throws IOException {
        Path dir = DATA_FILE.getParent();
        if (dir != null && Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.notExists(DATA_FILE)) {
            Files.createFile(DATA_FILE);
        }
    }

    /*
    Turn Tasks into txt form and save it into a txt file
     */
    private static void saveTasksToFile(List<Task> tasks) throws IOException {
        ensureDataFileExists();
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString()); // you will add this method in Task
        }
        Files.write(DATA_FILE, lines, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
    /*
    This function the saved .txt file and reinitializes any saved Tasks into Java objects
     */
    private static List<Task> loadTasksFromFile() throws IOException {
        ensureDataFileExists();
        List<String> lines = Files.readAllLines(DATA_FILE, StandardCharsets.UTF_8);

        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            try {
                String[] parts = line.split(",");
                if (parts[0].equals("T")) {
                    tasks.add(new ToDos(parts[1]));
                } else if (parts[0].equals("D")) {
                    tasks.add(new Deadlines(parts[1], parts[2]));
                } else if (parts[0].equals("E")) {
                    tasks.add(new Events(parts[1], parts[2], parts[3]));
                }
            } catch (Exception e) {
                System.out.println("Skipping corrupted line: " + line);
            }
        }
        return tasks;
    }



}

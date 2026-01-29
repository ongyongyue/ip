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
    public static void main(String[] args) throws HolidayException{
        String greeting = "\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------\n";
        System.out.println(greeting);
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        List<Task> lst = new ArrayList<>();
        while(true) {
            message = scanner.nextLine();
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
                        listOut(lst);
                    } else if (command[0].toLowerCase().equals("mark")) {
                        mark(lst, Integer.parseInt(command[1]));
                    } else if (command[0].toLowerCase().equals("unmark")) {
                        unmark(lst, Integer.parseInt(command[1]));
                    } else if (command[0].toLowerCase().equals("todo")) {
                        ToDos currentTask = new ToDos(command[1]);
                        lst.add(currentTask);
                        System.out.println(
                                "\t--------------------------------------------\n"
                                        + "\tGot it. I've added this task:\n"
                                        + "\t"
                                        + String.format(
                                        "  %s\n\tNow you have %d Tasks in the list\n",
                                        currentTask.toString(),
                                        lst.size())
                                        + "\t--------------------------------------------"
                        );
                    } else if (command[0].toLowerCase().equals("deadline")) {
                        String[] segment = command[1].split("/");
                        if (segment.length < 2) throw new HolidayException("Improper Command format");
                        String[] dueBy = segment[1].split(" ", 2);
                        Deadlines currentTask = new Deadlines(segment[0], dueBy[1]);
                        lst.add(currentTask);
                        System.out.println(
                                "\t--------------------------------------------\n"
                                        + "\tGot it. I've added this task:\n"
                                        + "\t"
                                        + String.format(
                                        "  %s\n\tNow you have %d Tasks in the list\n",
                                        currentTask.toString(),
                                        lst.size())
                                        + "\t--------------------------------------------"
                        );

                    } else if (command[0].toLowerCase().equals("event")) {
                        String[] segment = command[1].split("/");
                        if (segment.length < 2) throw new HolidayException("Improper Command format");
                        String[] start = segment[1].split(" ", 2);
                        String[] end = segment[2].split(" ", 2);

                        Events currentTask = new Events(segment[0], start[1], end[1]);
                        lst.add(currentTask);
                        System.out.println(
                                "\t--------------------------------------------\n"
                                        + "\tGot it. I've added this task:\n"
                                        + "\t"
                                        + String.format(
                                        "  %s\n\tNow you have %d Tasks in the list\n",
                                        currentTask.toString(),
                                        lst.size())
                                        + "\t--------------------------------------------"
                        );

                    } else if (command[0].toLowerCase().equals("delete")) {
                        if (lst.size() == 0) throw new HolidayException("List is empty!!");
                        if (command.length < 2) throw new HolidayException("Improper Command format");
                        delete(lst, Integer.parseInt(command[1]));

                    } else {
                        lst.add(new Task(message));
                        System.out.println(
                                "\t--------------------------------------------\n"
                                        + "\t"
                                        + "added: "
                                        + message
                                        + "\n"
                                        + "\t--------------------------------------------\n"
                        );
                    }


            } catch (HolidayException e) {
            System.out.println(
                    "\t--------------------------------------------\n"
                            + "\t"
                            + e.getMessage()
                            + "\n"
                            + "\t--------------------------------------------"
            );
            }
        }
        System.out.println(
                "\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------"
        );
    }

    public static void listOut(List<Task> lst) {
        Iterator<Task> iList = lst.iterator();
        int n = 1;
        System.out.println("\t--------------------------------------------\n"
                + "\tHere are the tasks in your list:");
        while(iList.hasNext()) {
            Task currentTask = iList.next();
            System.out.printf("\t%d.%s\n",n, currentTask.toString());
            n++;
        }
        System.out.println("\t--------------------------------------------");
    }

    public static void mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        System.out.println("\t--------------------------------------------\n"
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }

    public static void unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        System.out.println("\t--------------------------------------------\n"
                + "\t OK, I've marked this task as not done yet:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }

    public static void delete(List<Task> iList, int index) {
        Task removedTask = iList.remove(index - 1);
        System.out.println("\t--------------------------------------------\n"
                + "\t Noted. I've removed this task:");
        System.out.printf("\t %s\n\tNow you have %d Tasks Remaining",
                removedTask.toString(),
                iList.size()
                );
        System.out.println("\t--------------------------------------------");
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


}

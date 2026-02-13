package holiday;

public class Parser {
    public enum CommandType {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        BYE;
    }
    /*
    Parses the input command throws HolidayException if command is invalid
     */
    public static Command parseCommand(String fullCommand) throws HolidayException {
        String[] command = fullCommand.trim().split(" ", 2);
        String commandWord = command[0];

        switch (commandWord) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(Integer.parseInt(command[1]));

            case "unmark":
                return new UnmarkCommand(Integer.parseInt(command[1]));

            case "todo":
                if (command.length < 2 || command[1].isBlank()) {
                    throw new HolidayException("Description cannot be blank");
                }
                return new TodoCommand(command[1]);

            case "delete":
                if (command.length < 2) {
                    throw new HolidayException("Please provide index");
                }
                return new DeleteCommand(Integer.parseInt(command[1]));

            case "find":
                if (command.length < 2 || command[1].isBlank()) {
                    throw new HolidayException("Please provide a keyword.");
                }
                return new FindCommand(command[1]);
        }
        return new UnknownCommand();
    }
    /*
    Parses Tasks that are saved in file text form (ie: T,Help Mom)
     */
    public static String[] parseSavedTasks (String savedTasks) {
        String[] parts = savedTasks.split(",");
        return parts;
    }
    /*
    Parses commands for Deadline and Event Tasks
     */
    public static String[] parseTimedEvent(String[] command) throws HolidayException {
        String[] segment = command[1].split("/");
        if (segment.length < 2) {
            throw new HolidayException("Improper Command format");
        } else if (command[0].equalsIgnoreCase("deadline")) {
            String[] dueBy = segment[1].split(" ", 2);
            return new String[]{segment[0], dueBy[1]};
        } else if (command[0].equalsIgnoreCase("event")) {
            String[] start = segment[1].split(" ", 2);
            String[] end = segment[2].split(" ", 2);
            return new String[] {segment[0], start[1], end[1]};
        }
        return new String[] {null};
    }
    /*
    Check if an input is a valid command
     */
    public static boolean isValidCommand(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public static String parseFindKeyword(String input) throws HolidayException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new HolidayException("Please provide a keyword to search for.");
        }
        return parts[1].trim();
    }

}

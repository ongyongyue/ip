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
    public static String[] parseCommand(String fullCommand) throws HolidayException {
        String[] command = fullCommand.trim().split(" ", 2);
        if (command.length == 0 || command[0].isBlank()) {
            throw new HolidayException("Command cannot be blank");
        } else if (!command[0].toLowerCase().equals("bye")
                && !command[0].toLowerCase().equals("list")
                && command.length < 2) throw new HolidayException("Description can't be blank");
        return command;
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

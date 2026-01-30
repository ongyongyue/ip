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
        BYE;
    }
    public static String[] parseCommand(String fullCommand) throws HolidayException {
        String[] command = fullCommand.trim().split(" ", 2);
        if (command.length == 0 || command[0].isBlank()) {
            throw new HolidayException("Command cannot be blank");
        } else if (!command[0].toLowerCase().equals("bye")
                && !command[0].toLowerCase().equals("list")
                && !command[0].toLowerCase().equals("delete")
                && command.length < 2) throw new HolidayException("Description can't be blank");
        return command;
    }

    public static String[] parseSavedTasks (String savedTasks) {
        String[] parts = savedTasks.split(",");
        return parts;
    }

    public static boolean isValidCommand(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

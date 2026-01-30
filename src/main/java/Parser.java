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
        }
        return command;
    }

    public static String[] parseSavedTasks (String savedTasks) {
        String[] parts = savedTasks.split(",");
        return parts;
    }

    public static boolean isValidCommand(String input) {
        for (Parser.CommandType type : Parser.CommandType.values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

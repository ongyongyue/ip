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
        assert command != null : "Parser should always return a command";

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


}

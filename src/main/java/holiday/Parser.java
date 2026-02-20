package holiday;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses user input into a Command object.
     *
     * @param fullCommand full user input
     * @return corresponding Command object
     * @throws HolidayException if command is invalid
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
            default:
                return new UnknownCommand();
        }
    }

    /**
     * Parses a saved task line from the data file.
     *
     * @param savedTasks line read from file
     * @return components of the saved task
     */
    public static String[] parseSavedTasks(String savedTasks) {
        return savedTasks.split(",");
    }
}
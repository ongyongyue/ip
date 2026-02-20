package holiday;

import java.util.List;
import java.util.Scanner;

/**
 * Handles formatting and generation of all user interface messages.
 * Responsible for displaying feedback, errors, and task-related outputs.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Returns the welcome message shown when the program starts.
     *
     * @return formatted welcome message
     */
    public String getWelcomeMessage() {
        return( "Hello! I'm Holiday\n"
                + "How can I help you?\n");
    }

    /**
     * Returns the goodbye message shown when the program exits.
     *
     * @return formatted goodbye message
     */
    public String getGoodbyeMessage() {
        return( "Bye! See you again!");
    }

    /**
     * Formats user input inside separator lines.
     *
     * @param input text to display
     * @return formatted string with borders
     */
    public static String getInputFormat(String input) {
        return (String.format("%s\n", input));
    }

    /**
     * Returns a standard separator line.
     *
     * @return formatted separator line
     */
    private String line() {
        return "\t--------------------------------------------\n";
    }

    /**
     * Returns a formatted error message.
     *
     * @param msg error message text
     * @return formatted error output
     */
    public String getErrorMessage(String msg) {
        return (msg + "\n");
    }

    /**
     * Returns a message describing the correct date-time format.
     *
     * @return date-time format error message
     */
    public String getFormatErrorMessage() {
        return "Wrong Date time format, it should be yyyy-mm-dd HHmm\n"
                + "For example : 2019-08-29 1800";
    }

    /**
     * Returns a confirmation message when a task is added.
     *
     * @param lst current task list
     * @param currentTask task that was added
     * @return formatted add task message
     */
    public String getAddTaskMessage(List<Task> lst, Task currentTask) {
        return "Got it. I've added this task:\n"
                + currentTask + "\n"
                + "Now you have " + lst.size() + " Tasks in the list\n";
    }

    /**
     * Returns the formatted list of tasks.
     *
     * @param lst list of tasks
     * @return formatted task list
     * @throws HolidayException if the list is empty
     */
    public String getListMessage(List<Task> lst) throws HolidayException {
        if (lst.isEmpty()) {
            throw new HolidayException("List is empty!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        int i = 1;
        for (Task task : lst) {
            sb.append(i++).append(".").append(task).append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns a message confirming a task has been marked as done.
     *
     * @param lst task list
     * @param index index of the task marked
     * @return formatted mark confirmation message
     */
    public String getMarkMessage(List<Task> lst, int index) {
        Task task = lst.get(index - 1);
        return  "Nice! I've marked this task as done:\n"
                + "\t" + task + "\n";
    }

    /**
     * Returns a message confirming a task has been unmarked.
     *
     * @param lst task list
     * @param index index of the task unmarked
     * @return formatted unmark confirmation message
     */
    public String getUnmarkMessage(List<Task> lst, int index) {
        Task task = lst.get(index - 1);
        return "OK, I've marked this task as not done yet:\n"
                + "\t " + task + "\n";
    }

    /**
     * Returns a confirmation message when a task is deleted.
     *
     * @param lst updated task list
     * @param removedTask task that was removed
     * @return formatted delete confirmation
     */
    public String getDeleteTaskMessage(List<Task> lst, Task removedTask) {
        return  "Noted. I've removed this task:\n"
                + "\t " + removedTask + "\n"
                + "Now you have " + lst.size() + " Tasks Remaining\n";
    }

    /**
     * Returns tasks matching a keyword search.
     *
     * @param matches TaskList containing matched tasks
     * @return formatted matching tasks output
     */
    public String getMatchingTasksMessage(TaskList matches) {
        StringBuilder sb = new StringBuilder();

        if (matches.size() == 0) {
            sb.append("There are no Tasks Matching this keyword\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= matches.size(); i++) {
                sb.append("\t").append(i).append(".").append(matches.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
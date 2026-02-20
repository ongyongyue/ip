package holiday;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    /*
    Display welcome message
     */
    public String getWelcomeMessage() {
        return("\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------");
    }
    /*
    Display goodbye message
     */
    public String getGoodbyeMessage() {
        return("\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------");
    }
    public static String getInputFormat(String input) {
        return ("--------------------------------------------\t\n"
                + String.format("%s\t\t\n", input)
                + "--------------------------------------------\t");
    }
    public String getEchoMessage(String input) {
        return input;
    }
    /*
    Displays a line
     */
    public void showLine() {
        System.out.println("\t--------------------------------------------");
    }
    /*
    Read commands
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /*
    Show error message
     */
    public void showError(String msg) {
        System.out.println("\t--------------------------------------------\n"
                + "\t" + msg + "\n"
                + "\t--------------------------------------------");
    }
    /*
    Print any message
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }
    /*
    Print message when task is added
     */
    public void addTaskMessage(List<Task> lst, Task currentTask) {
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
    }
    /*
    Error Message to display when format is wrong
     */
    public void showFormatErrorMessage() {
        System.out.println("\tWrong Date time format, it should be yyyy-mm-dd HHmm" +
                "\n For example : 2019-08-29 1800");
    }

    /*
    Lists out tasks in the list
     */
    public static void listOut(List<Task> lst) throws HolidayException {
        Iterator<Task> iList = lst.iterator();
        int n = 1;
        if (lst.size() == 0) {
            throw new HolidayException("List is empty!!");
        } else {
            System.out.println("\t--------------------------------------------\n"
                    + "\tHere are the tasks in your list:");
            while(iList.hasNext()) {
                Task currentTask = iList.next();
                System.out.printf("\t%d.%s\n",n, currentTask.toString());
                n++;
            }
            System.out.println("\t--------------------------------------------");
        }

    }

    /*
    Mark the indexed task in the TaskList iList as done
     */


    /*
    Display delete message when deleted specified Task
     */
    public static void deleteTaskMessage(List<Task> iList, Task removedTask) {
        System.out.println("\t--------------------------------------------\n"
                + "\t Noted. I've removed this task:");
        System.out.printf("\t %s\n\tNow you have %d Tasks Remaining\n",
                removedTask.toString(),
                iList.size()
        );
        System.out.println("\t--------------------------------------------");
    }

    /*
    Displays tasks matching the keyword
     */
    public void showMatchingTasks(TaskList matches) {

        if (matches.size() > 0) {
            System.out.println("\t--------------------------------------------");
            System.out.println("\tHere are the matching tasks in your list:");

            for (int i = 1; i < matches.size() + 1; i++) {
                System.out.printf("\t%d.%s\n",i, matches.get(i));
            }

            System.out.println("\t--------------------------------------------");
        } else {
            System.out.println("\t--------------------------------------------");
            System.out.println("\t There are no Tasks Matching this keyword");
            System.out.println("\t--------------------------------------------");
        }
    }
    private String line() {
        return "\t--------------------------------------------\n";
    }

    public String getErrorMessage(String msg) {
        return line()
                + "\t" + msg + "\n"
                + line();
    }

    public String getFormatErrorMessage() {
        return "\tWrong Date time format, it should be yyyy-mm-dd HHmm\n"
                + "\tFor example : 2019-08-29 1800";
    }

    public String getAddTaskMessage(List<Task> lst, Task currentTask) {
        return line()
                + "\tGot it. I've added this task:\n"
                + "\t  " + currentTask + "\n"
                + "\tNow you have " + lst.size() + " Tasks in the list\n"
                + line();
    }

    public String getListMessage(List<Task> lst) throws HolidayException {
        if (lst.isEmpty()) {
            throw new HolidayException("List is empty!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(line())
                .append("\tHere are the tasks in your list:\n");

        int i = 1;
        for (Task task : lst) {
            sb.append("\t").append(i++).append(".").append(task).append("\n");
        }

        sb.append(line());
        return sb.toString();
    }

    public String getMarkMessage(List<Task> lst, int index) {
        Task task = lst.get(index - 1);
        return line()
                + "\tNice! I've marked this task as done:\n"
                + "\t " + task + "\n"
                + line();
    }

    public String getUnmarkMessage(List<Task> lst, int index) {
        Task task = lst.get(index - 1);
        return line()
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t " + task + "\n"
                + line();
    }

    public String getDeleteTaskMessage(List<Task> lst, Task removedTask) {
        return line()
                + "\tNoted. I've removed this task:\n"
                + "\t " + removedTask + "\n"
                + "\tNow you have " + lst.size() + " Tasks Remaining\n"
                + line();
    }

    public String getMatchingTasksMessage(TaskList matches) {
        StringBuilder sb = new StringBuilder();
        sb.append(line());

        if (matches.size() == 0) {
            sb.append("\tThere are no Tasks Matching this keyword\n");
        } else {
            sb.append("\tHere are the matching tasks in your list:\n");
            for (int i = 1; i <= matches.size(); i++) {
                sb.append("\t").append(i).append(".").append(matches.get(i)).append("\n");
            }
        }

        sb.append(line());
        return sb.toString();
    }


}

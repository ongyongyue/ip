package holiday;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    /*
    Display welcome message
     */
    public void  showWelcome() {
        System.out.println("\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------");
    }
    /*
    display goodbye message
     */
    public void showGoodbye() {
        System.out.println("\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------");
    }
    /*
    displays a line
     */
    public void showLine() {
        System.out.println("\t--------------------------------------------");
    }
    /*
    read commands
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /*
    show error message
     */
    public void showError(String msg) {
        System.out.println("\t--------------------------------------------\n"
                + "\t" + msg + "\n"
                + "\t--------------------------------------------");
    }
    /*
    print any message
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }
    /*
    print message when task is added
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
    function to list out items in the list
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
    function to mark items as done
     */
    public static void mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        System.out.println("\t--------------------------------------------\n"
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }

    /*
    function to unmark done items
     */
    public static void unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        System.out.println("\t--------------------------------------------\n"
                + "\t OK, I've marked this task as not done yet:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }

    /*
    display delete message
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


}

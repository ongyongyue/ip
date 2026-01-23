import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Holiday {
    public static void main(String[] args) {
        String greeting = "\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------\n";
        System.out.println(greeting);
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        List<Task> lst = new ArrayList<>();
        while(true){
            message = scanner.nextLine();
            String[] command = message.split(" ", 2);

            if(command[0].toLowerCase().equals("bye")) {
                break;
            } else if(command[0].toLowerCase().equals("list")) {
                listOut(lst);
            } else if (command[0].toLowerCase().equals("mark")) {
                mark(lst, Integer.parseInt(command[1]));
            } else if (command[0].toLowerCase().equals("unmark")) {
                unmark(lst, Integer.parseInt(command[1]));
            } else if (command[0].toLowerCase().equals("todo")) {
                lst.add(new ToDos(command[1]));
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

        }
        System.out.println(
                "\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------\n"
        );
    }

    public static void listOut(List<Task> lst) {
        Iterator<Task> iList = lst.iterator();
        int n = 1;
        System.out.println("\t--------------------------------------------\n"
                + "\tHere are the tasks in your list: \n");
        while(iList.hasNext()) {
            Task currentTask = iList.next();
            System.out.printf("\t%d.%s\n",n, currentTask.toString());
            n++;
        }
        System.out.println("\t--------------------------------------------\n");
    }

    public static void mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        System.out.println("\t--------------------------------------------\n"
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------\n");
    }

    public static void unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        System.out.println("\t--------------------------------------------\n"
                + "\t OK, I've marked this task as not done yet:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------\n");
    }
}

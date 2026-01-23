import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Holiday {
    public static void main(String[] args) throws HolidayException{
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
            try {
                if (args == null) {
                    throw new HolidayException("No blank entries");
                } else if (
                        !(
                            command[0].toLowerCase().equals("list")
                            || command[0].toLowerCase().equals("list")
                            || command[0].toLowerCase().equals("mark")
                            || command[0].toLowerCase().equals("unmark")
                            || command[0].toLowerCase().equals("todo")
                            || command[0].toLowerCase().equals("deadline")
                            || command[0].toLowerCase().equals("event")
                        )
                )
                {
                    throw new HolidayException("Sorry, I don't recognise this command");
                } else if (command.length < 2) {
                    throw new HolidayException("Description can't be blank");
                }
            } catch (HolidayException e) {
                    System.out.println(
                            "\t--------------------------------------------\n"
                            + "\t"
                            + e.getMessage()
                            + "\n"
                            + "\t--------------------------------------------"
                    );
                    continue;

            }

            if(command[0].toLowerCase().equals("bye")) {
                break;
            } else if(command[0].toLowerCase().equals("list")) {
                listOut(lst);
            } else if (command[0].toLowerCase().equals("mark")) {
                mark(lst, Integer.parseInt(command[1]));
            } else if (command[0].toLowerCase().equals("unmark")) {
                unmark(lst, Integer.parseInt(command[1]));
            } else if (command[0].toLowerCase().equals("todo")) {
                ToDos currentTask = new ToDos(command[1]);
                lst.add(currentTask);
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
            } else if (command[0].toLowerCase().equals("deadline")) {
                String[] segment = command[1].split("/");
                String[] dueBy = segment[1].split(" ", 2);
                Deadlines currentTask = new Deadlines(segment[0], dueBy[1]);
                lst.add(currentTask);
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

            } else if (command[0].toLowerCase().equals("event")) {
                String[] segment = command[1].split("/");
                String[] start = segment[1].split(" ", 2);
                String[] end = segment[2].split(" ", 2);

                Events currentTask = new Events(segment[0], start[1], end[1]);
                lst.add(currentTask);
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

            }else {
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
                + "\t--------------------------------------------"
        );
    }

    public static void listOut(List<Task> lst) {
        Iterator<Task> iList = lst.iterator();
        int n = 1;
        System.out.println("\t--------------------------------------------\n"
                + "\tHere are the tasks in your list:");
        while(iList.hasNext()) {
            Task currentTask = iList.next();
            System.out.printf("\t%d.%s\n",n, currentTask.toString());
            n++;
        }
        System.out.println("\t--------------------------------------------");
    }

    public static void mark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setDone();
        System.out.println("\t--------------------------------------------\n"
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }

    public static void unmark(List<Task> iList, int index) {
        Task currentTask = iList.get(index - 1);
        currentTask.setUndone();
        System.out.println("\t--------------------------------------------\n"
                + "\t OK, I've marked this task as not done yet:");
        System.out.printf("\t %s\n", currentTask.toString());
        System.out.println("\t--------------------------------------------");
    }
}

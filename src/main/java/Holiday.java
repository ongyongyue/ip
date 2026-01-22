import java.lang.reflect.Array;
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
        List<Item> lst = new ArrayList<>();
        while(true){
            message = scanner.nextLine();
            String[] command = message.split(" ");

            if(command[0].toLowerCase().equals("bye")) {
                break;
            } else if(command[0].toLowerCase().equals("list")) {
                listOut(lst);
            } else if (command[0].toLowerCase().equals("mark")) {
                mark(lst, Integer.parseInt(command[1]));
                listOut(lst);
            } else {
                    lst.add(new Item(message));
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

    public static void listOut(List<Item> lst) {
        Iterator<Item> iList = lst.iterator();
        int n = 1;
        System.out.println("\t--------------------------------------------\n"
                + "\tHere are the tasks in your list: \n");
        while(iList.hasNext()) {
            Item currentItem = iList.next();
            String checkBox = currentItem.getIsDone() ? "[X]" : "[ ]";
            System.out.printf("\t%d.%s %s\n",n,checkBox,currentItem.getName());
            n++;
        }
        System.out.println("\t--------------------------------------------\n");
    }

    public static void mark(List<Item> iList, int index) {
        iList.get(index - 1).setDone();
    }

    public static void unmark(List<Item> iList, int index) {
        iList.get(index - 1).setUndone();
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Holiday {
    public static void main(String[] args) {

        String greeting = "\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------\n";
        System.out.println(greeting);
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        List<String> lst = new ArrayList<>();
        boolean isBye = true;
        while(true){
            message = scanner.nextLine();

            isBye = message.toLowerCase().equals("bye");

            if(isBye) break;
            lst.add(message);
            System.out.println(
                    "\t--------------------------------------------\n"
                    + "\t"
                    + "added: "
                    + message
                    + "\n"
                    + "\t--------------------------------------------\n"
            );

        }
        System.out.println(
                "\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------\n"
        );
    }
}

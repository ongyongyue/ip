import java.util.Locale;
import java.util.Scanner;

public class Holiday {
    public static void main(String[] args) {

        String greeting = "\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------\n";
        System.out.println(greeting);
        Scanner scanner  = new Scanner(System.in);
        String message = " ";
        boolean isBye = true;
        while(true){
            message = scanner.nextLine();

            isBye = message.toLowerCase().equals("bye");

            if(isBye) break;

            System.out.println(
                    "\t--------------------------------------------\n"
                    + "\t"
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

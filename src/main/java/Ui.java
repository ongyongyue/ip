import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("\t--------------------------------------------\n"
                + "\tHello! I'm Holiday\n"
                + "\tHow can I help you?\n"
                + "\t--------------------------------------------");
    }

    public void showGoodbye() {
        System.out.println("\t--------------------------------------------\n"
                + "\tBye! See you again!\n"
                + "\t--------------------------------------------");
    }

    public void showLine() {
        System.out.println("\t--------------------------------------------");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String msg) {
        System.out.println("\t--------------------------------------------\n"
                + "\t" + msg + "\n"
                + "\t--------------------------------------------");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}

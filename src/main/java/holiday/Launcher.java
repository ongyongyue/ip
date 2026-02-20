package holiday;

import javafx.application.Application;

/**
 * Launcher class used to start the JavaFX application.
 * This helps avoid classpath issues when running the GUI.
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
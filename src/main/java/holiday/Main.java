package holiday;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Holiday chatbot GUI.
 */
public class Main extends Application {

    private Holiday holidayBot = new Holiday();

    /**
     * Starts the JavaFX GUI.
     *
     * @param stage primary application stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHoliday(holidayBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
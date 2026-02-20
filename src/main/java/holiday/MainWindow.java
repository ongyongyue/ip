package holiday;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Holiday holiday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/drake.png"));
    private Image holidayChadImage = new Image(this.getClass().getResourceAsStream("/images/Gigachad.jpg"));

    /**
     * Initializes GUI components and displays the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(new Ui().getWelcomeMessage(), holidayChadImage)
        );
    }

    /**
     * Injects the Holiday chatbot instance.
     *
     * @param holiday chatbot instance
     */
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    /**
     * Handles user input, displays the chatbot response,
     * and exits the application when "bye" is entered.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = holiday.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(Ui.getInputFormat(input), userImage),
                DialogBox.getBotDialog(response, holidayChadImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.8));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
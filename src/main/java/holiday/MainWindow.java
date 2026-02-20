package holiday;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Ui().getWelcomeMessage(), holidayChadImage)
        );

    }

    /**
     * Injects the Holiday instance
     */
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = holiday.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(Ui.getInputFormat(input), userImage),
                DialogBox.getDukeDialog(response, holidayChadImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.8));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}


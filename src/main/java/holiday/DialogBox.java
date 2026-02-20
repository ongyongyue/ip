package holiday;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

/**
 * Represents a dialog box in the GUI consisting of
 * a speaker image and a text message.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean userStyle) {
        String fxmlFile = userStyle ? "/view/DialogBox.fxml" : "/view/BotDialogBox.fxml";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlFile));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getBotDialog(String text, Image img) {
        // No flip() needed — BotDialogBox.fxml already has avatar on the left
        return new DialogBox(text, img, false);
    }
}
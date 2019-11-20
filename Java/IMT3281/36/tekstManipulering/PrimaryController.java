package no.ntnu.stiabra.uke36lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private RadioButton radButUnchanged;

    @FXML
    private RadioButton radButSmall;

    @FXML
    private RadioButton radButLarge;

    @FXML
    private RadioButton radButBack;

    @FXML
    private TextArea outputField;

    @FXML
    private TextField inputField;

    @FXML
    private Button textButton;

    /**
     * Sends text-strings from inputField to outputField (via the transformText-function).
     * @param event
     */
    @FXML
    void sendInput(ActionEvent event) {
        outputField.appendText(transformText(inputField.getText())+"\n");
        inputField.setText("");
    }

    /**
     * Takes a string, and transforms it according to which button is selected.
     * @param text The text-string to transform.
     * @return Returns the transformed text-string.
     */
    String transformText(String text) {
        if (radButUnchanged.isSelected()) {
         return (text);
        }

        else if (radButSmall.isSelected()) {
            return (text.toLowerCase());
        }

        else if (radButLarge.isSelected()) {
            return (text.toUpperCase());
        }

        return (new StringBuilder().append(text).reverse().toString());
    }

}
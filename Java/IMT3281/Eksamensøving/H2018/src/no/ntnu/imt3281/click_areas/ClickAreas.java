package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ClickAreas {
    @FXML
    public Label areaToClick;

    @FXML
    public TextArea results;

    public void clicked(MouseEvent mouseEvent) {
    }
}

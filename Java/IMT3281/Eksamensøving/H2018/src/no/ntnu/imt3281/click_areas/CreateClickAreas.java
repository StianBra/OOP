package no.ntnu.imt3281.click_areas;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class containing code for defining new figures based on x/y coordinates from user clicks
 */
public class CreateClickAreas {
    @FXML
    public ListView points;

    @FXML
    public TextField areaName;

    @FXML
    public TextArea areas;

    @FXML
    public AnchorPane imageContainer;

    private JSONObject shapes;

    private Polygon polygon;

    /**
     * Sets up the shapes/polygon objects, and adds highlighting around the figure a user clicks around
     */
    @FXML
    public void initialize() {
        shapes = new JSONObject();
        polygon = new Polygon();

        polygon.setVisible(true);
        polygon.setStyle("-fx-stroke: rgba(0, 0, 0, 1); -fx-fill: rgba(100%, 100%, 100%, 0.5);");

        imageContainer.getChildren().add(polygon);
    }

    /**
     * The code that runs after a user has added a new figure,
     * gets the coordinates for the figure and appends them to the textarea in the bottom of the application
     */
    @FXML
    public void addArea() {
        // Adds the name of the figure
        shapes.put("name", areaName.getText());
        areaName.setText("");

        // JSONArray to store all the points (x/y)
        JSONArray p = new JSONArray();

        // ObservableList of all the points
        ObservableList<Double> list = polygon.getPoints();

        // Iterates through the observable list, fetches every point and adds them to the JSONArray p
        for (int i = 0; i < list.size() / 2; i++) {
            JSONObject point = new JSONObject();
            point.put("x", list.get(i));
            point.put("y", list.get(i + 1));
            p.put(point);
        }

        // Adds the whole p JSONArray to the shapes JSONObject
        shapes.put("polygon", p);

        // Updates the textarea in the bottom of the application with the new figure
        areas.setText(areas.getText() + shapes + "\n");

        // Reset fields
        points.getItems().clear();
        polygon.getPoints().clear();
    }

    /**
     * Outputs the shapes JSONObject to file
     */
    @FXML
    public void toFile() {
        // Sets up the filechooser, saving the file as a json-file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Opens the file dialogue in the same window container as the main application
        File file = fileChooser.showSaveDialog(imageContainer.getParent().getScene().getWindow());

        if (file != null) {
            PrintWriter writer = null;

            // Tries to open the file for writing
            try {
                writer = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert writer != null;

            // Writes the content of the bottom textfield in the app to the file
            writer.println(areas.getText());
            writer.close();
        }
    }

    /**
     * Finds the x/y coordinates for a mouse click
     * @param mouseEvent the mouse-click event
     */
    @FXML
    public void findPos(MouseEvent mouseEvent) {
        double x = mouseEvent.getSceneX() - 10;
        double y = mouseEvent.getSceneY() - 10;

        points.getItems().add(x + " : " + y);

        polygon.getPoints().add(x);
        polygon.getPoints().add(y);
    }
}

package no.ntnu.imt3281.H2018;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Use GameOfLife.fxml as the main window-layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        AnchorPane root = loader.load();

        // Define what closing the window will do (Simply close everything)
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Load the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        WeatherController weather = loader.getController();
        stage.setTitle("Værmelding");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
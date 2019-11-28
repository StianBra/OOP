package no.ntnu.imt3281.S2018;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOfLife.fxml"));
        AnchorPane root = loader.load();

        // Define what closing the window will do (Simply close everything)
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Load the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        GameOfLifeController gameOfLife = loader.getController();
        stage.setTitle("Game of Life");
        stage.show();
        gameOfLife.drawGrid();
    }

    public static void main(String[] args) {
        launch();
    }
}
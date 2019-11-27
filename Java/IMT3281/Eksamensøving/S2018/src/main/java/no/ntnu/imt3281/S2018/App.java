package no.ntnu.imt3281.S2018;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Use GameOfLife.fxml as the main window-layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOfLife.fxml"));
        BorderPane root = loader.load();

        // Define what closing the window will do (Simply close everything)
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Load the scene
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
package no.ntnu.imt3281.weather;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load resources
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        SplitPane root = loader.load();

        // Define what closing the window will do (Simply close everything)
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Set the window stuff
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Værmeldingen");
        primaryStage.show();
    }

    /**
     * Fallback entry-point if the start-function was not started first
     * @param args Commandline-arguments, not relevant as they will not be used
     */
    public static void main(String[] args) {
        launch(args);
    }
}

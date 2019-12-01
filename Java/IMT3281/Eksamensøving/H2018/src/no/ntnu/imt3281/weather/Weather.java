package no.ntnu.imt3281.weather;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import no.ntnu.imt3281.yr_places.Database;
import no.ntnu.imt3281.yr_places.Place;

/**
 * Class used to display weather-information for each yr place
 */
public class Weather {

    @FXML
    private WebView map;

    @FXML
    private WebView forecast;

    /**
     * Sets up the map webpage and how to handle alerts from the page
     */
    @FXML
    public void initialize() {
        WebEngine webEngine = map.getEngine();
        // Ideally this link should be used, however it only works on mac:
        // Issue: https://bitbucket.org/okolloen/imt3281-eksamen-hosten-2018/issues/9/oppgave-5-webview-om-kartet-ikke-vises
        // webEngine.load("http://folk.ntnu.no/oeivindk/imt3281/map/");
        webEngine.load("http://folk.ntnu.no/oeivindk/imt3281/map/pseudoMap.html");

        // Gets an alert from the webpage loaded above (which contains x/y coordinates as a tab-separated string),
        // and prints out the name and municipality of the nearest place in the database
        webEngine.setOnAlert(handler -> {
            String[] data = handler.getData().split("\t");
            System.out.println("X: " + data[0] + "\nY: " + data[1]);

            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);

            Database database = Database.getDb();
            Place nearestPlace = database.findClosestPlace(x, y);
            System.out.println("Funnet nærmeste sted: " + nearestPlace.getStedsnavn() + ", " + nearestPlace.getKommune() + "\n");
        });
    }
}

package no.ntnu.imt3281.yr_places;

import no.ntnu.imt3281.weather.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class
 */
public class Main {

    /**
     * Main program for using place/database to get all places from yr, and store it in a local db
     * @param args Command-line arguments, not used
     */
    public static void main(String[] args) {
        //getAndStorePlaceInformation();
    }

    /**
     * Fetches all of the data from http://fil.nrk.no/yr/viktigestader/noreg.txt, and stores it in a local database
     */
    private static void getAndStorePlaceInformation() {
        ArrayList<List<String>> places = (ArrayList<List<String>>) Place.getPlaceInformation();
        Database database = Database.getDb();

        for (int i = 1; i < Place.getPlaceInformation().size(); i++) {
            System.out.println("Adding place #" + i + " to the database...");
            database.addPlace(new Place(places.get(i)));
        }
    }
}

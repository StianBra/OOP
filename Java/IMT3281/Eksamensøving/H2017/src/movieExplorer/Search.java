package movieExplorer;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

import static movieExplorer.Constants.APIKEY;

public class Search {

    public Search() {

    }


    /**
     * Performs a multi-search (search with multiple results) after an actor
     * @param actorName The actor to search for
     * @return An instance of the custom JSON class with the data
     */
    public static JSON multiSearch(String actorName) {
        String response = null;
        String request = "https://api.themoviedb.org/3/search/multi?api_key=APIKEY&language=en-US&query=ACTOR&page=1&include_adult=false";
        request = request.replace("APIKEY", APIKEY);
        actorName = actorName.replace(" ", "%20");
        request = request.replace("ACTOR", actorName);

        System.out.println("Search request: " + request);

        try {
            response = Unirest.get(request).asString().getBody();
            System.out.println(response);		// Outputs JSON response from server.
            Unirest.shutdown();			// Must be called at the end of the application
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }

        return new JSON(response);
    }

    /**
     * Performs a search for actors in a given movie
     * @param movieIndex The index of the movie to find actors for
     * @return An instance of the custom JSON class with data about the actors
     */
    public static JSON actors(int movieIndex) {
        String response = null;
        String request = "https://api.themoviedb.org/3/movie/MOVIEID/credits?api_key=APIKEY";
        request = request.replace("APIKEY", APIKEY);
        request = request.replace("MOVIEID", String.valueOf(movieIndex));

        System.out.println("Search request: " + request);

        try {
            response = Unirest.get(request).asString().getBody();
            System.out.println(response);		// Outputs JSON response from server.
            Unirest.shutdown();			// Must be called at the end of the application
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }

        return new JSON(response);
    }
}

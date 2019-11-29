package no.ntnu.imt3281.yr_places;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Place {

    private int kommunenr;
    private String stedsnavn;
    private String stedstype;
    private String kommune;
    private String fylke;
    private double lat;
    private double lng;
    private String varselURL;

    /**
     * Constructor for a place
     * @param info List of different variables, see below
     */
    public Place(List<String> info) {
        kommunenr = Integer.parseInt(info.get(0));
        stedsnavn = info.get(1);
        stedstype = info.get(4);
        kommune = info.get(6);
        fylke = info.get(7);
        lat = Double.parseDouble(info.get(8));
        lng = Double.parseDouble(info.get(9));

        String temp = info.get(12);

        // If there is a bokmål forecast, use it
        if (temp.endsWith(".xml")) {
            varselURL = temp;
        } else {
            // Use nynorsk if there is no bokmål forecast
            varselURL = info.get(11);
        }
    }

    public int getKommunenr() {
        return kommunenr;
    }

    public void setKommunenr(int kommunenr) {
        this.kommunenr = kommunenr;
    }

    public String getStedsnavn() {
        return stedsnavn;
    }

    public void setStedsnavn(String stedsnavn) {
        this.stedsnavn = stedsnavn;
    }

    public String getStedstype() {
        return stedstype;
    }

    public void setStedstype(String stedstype) {
        this.stedstype = stedstype;
    }

    public String getKommune() {
        return kommune;
    }

    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    public String getFylke() {
        return fylke;
    }

    public void setFylke(String fylke) {
        this.fylke = fylke;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getVarselURL() {
        return varselURL;
    }

    public void setVarselURL(String varselURL) {
        this.varselURL = varselURL;
    }


    /**
     * Gets the entire content of http://fil.nrk.no/yr/viktigestader/noreg.txt as a List of Lists of strings
     * (The list is the entire file, the sublist is one line of strings, and each string is one tab-separated field)
     * @return The list of lists of strings based on the file above
     */
    public static List<List<String>> getPlaceInformation() {
        URL url = null;
        List<List<String>> noreg = new ArrayList<>();

        // Tries to store the URL
        try {
            url = new URL("http://fil.nrk.no/yr/viktigestader/noreg.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Makes sure the URL is not null (in case it malformedURLException'ed)
        assert url != null;

        // Tries to read each line in the file
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;

            // Reads until the file is completed
            while ((line = in.readLine()) != null) {
                // Adds one list of strings (one line) to the list containing the whole file
                noreg.add(Arrays.asList(line.split("\t")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Returns the whole file, as a list of lists of strings (tab-separated)
        return noreg;
    }
}

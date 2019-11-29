package no.ntnu.imt3281.yr_places;

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
}

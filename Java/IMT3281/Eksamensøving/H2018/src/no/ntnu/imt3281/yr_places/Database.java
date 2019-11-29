package no.ntnu.imt3281.yr_places;

import com.sun.javafx.collections.ArrayListenerHelper;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Database {
    private Connection con;
    private static Database db = null;

    /**
     * Creates a derby database on disk
     */
    private Database() {
        String dbURL = "jdbc:derby:./placesDB";

        try {
            con = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            if (e.getMessage().equals("Database './placesDB' not found.")) {
                try {
                    con = DriverManager.getConnection(dbURL + ";create=true");
                    createTable();
                } catch (SQLException ex) {
                    System.err.println("Could not create placesDB! " + ex.getMessage());
                }
            } else {
                System.err.println("Could not connect to placesDB! " + e.getMessage());
            }
        }
    }

    /**
     * Creates a derby database in memory
     * @param dbURL The URL for the memory-based database
     */
    private Database(String dbURL) {
        try {
            con = DriverManager.getConnection(dbURL);
            createTable();
        } catch (SQLException e) {
            System.err.println("Could not create placesDB in memory! " + e.getMessage());
        }
    }

    /**
     * Creates the table containing places information
     */
    private void createTable() {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE places (" +
                    "kommunenummer int NOT NULL," +
                    "stedsnavn varchar(255) NOT NULL," +
                    "stedstype varchar(255) NOT NULL," +
                    "kommune varchar(255) NOT NULL," +
                    "fylke varchar(255) NOT NULL," +
                    "lat float NOT NULL," +
                    "lng float NOT NULL," +
                    "varselURL varchar(255) NOT NULL)");
            stmt.execute("CREATE UNIQUE INDEX idx on places (kommunenummer, stedsnavn)");

        } catch(SQLException e) {
            System.err.println("Could not create tables in placesDB! " + e.getMessage());
        }
    }

    /**
     * Simpleton returner for database, stored on disk
     * @return The database which is stored on disk
     */
    public static Database getDb() {
        if (db == null) {
            db = new Database();
        }

        return db;
    }

    /**
     * Simpleton returner for database, stored in memory
     * @return The database which is stored in memory
     */
    public static Database getTempDb() {
        if (db == null) {
            db = new Database("jdbc:derby:memory:placesDB;create=true");
        }

        return db;
    }

    /**
     * Adds a new place to the place table in placesDB
     * @param newPlace The new place to add
     */
    public void addPlace(Place newPlace) {
        // First gets all the individual values from the new place
        int kommunenr = newPlace.getKommunenr();
        String stedsnavn = newPlace.getStedsnavn();
        String stedstype = newPlace.getStedstype();
        String kommune = newPlace.getKommune();
        String fylke = newPlace.getFylke();
        double lat = newPlace.getLat();
        double lng = newPlace.getLng();
        String varselURL = newPlace.getVarselURL();

        // Tries to insert all the variables into the places table
        try (Statement stmt = con.createStatement()) {
            stmt.execute("INSERT INTO places " +
                    "VALUES (" +
                    kommunenr + "," +
                    "\'" + stedsnavn + "\'" + "," +
                    "\'" + stedstype + "\'" + "," +
                    "\'" + kommune + "\'" + "," +
                    "\'" + fylke + "\'" + "," +
                    lat + "," +
                    lng + "," +
                    "\'" + varselURL + "\'" + ")");
        } catch (SQLException e) {
            System.err.println("Could not insert new place into the table in placesDB! " + e.getMessage());
        }
    }

    /**
     * Finds the place (in db) closest to a given x/y coordinate
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The place which was closest to (x, y) in the database
     */
    public Place findClosestPlace(double x, double y) {
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            // Index of the place closest to x/y coordinates
            int index = 0;

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM places")) {
                // Variable to track the smallest difference of x/y coordinates encountered so far
                double smallestDifference = 99999;

                // Iterates through every row in the resultset
                while (rs.next()) {
                    // Gets the lat/lng variables from the resultset
                    double lat = rs.getDouble(6);
                    double lng = rs.getDouble(7);

                    // Use the distance formula to caclulate the distance between x/y and lat/lng
                    double difference = sqrt(pow(x - lat, 2) + pow(y - lng, 2));

                    // If smaller than the (so far) smallest recorded distance
                    if (difference < smallestDifference) {
                        // Then we will store its index in the table
                        index = rs.getRow();
                        smallestDifference = difference;
                    }
                }

                // Sets the row to the index with smallest distance (closest place)
                rs.absolute(index);

                // Gets all the values from the row, and stores it in 'fields'
                ArrayList<String> fields = new ArrayList<>();
                fields.add(String.valueOf(rs.getInt(1)));
                fields.add(rs.getString(2));
                fields.add("");
                fields.add("");
                fields.add(rs.getString(3));
                fields.add("");
                fields.add(rs.getString(4));
                fields.add(rs.getString(5));
                fields.add(String.valueOf(rs.getDouble(6)));
                fields.add(String.valueOf(rs.getDouble(7)));
                fields.add("");
                fields.add("");
                fields.add(rs.getString(8));

                // Return the place with the given fields
                return new Place(fields);
            }
        } catch (SQLException e) {
            System.err.println("Could not find the closest place from the table in placesDB! " + e.getMessage());
        }

        return null;
    }
}

package no.ntnu.imt3281.yr_places;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            stmt.execute("CREATE UNIQUE INDEX idx on places (kommunenummer, stedsnavn");

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
            db = new Database("jdbc:derby:memory:places;create=true");
        }

        return db;
    }
}

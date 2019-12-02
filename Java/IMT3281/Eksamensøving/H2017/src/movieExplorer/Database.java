package movieExplorer;

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
        String dbURL = "jdbc:derby:./movieDB";

        try {
            con = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            if (e.getMessage().equals("Database './movieDB' not found.")) {
                try {
                    con = DriverManager.getConnection(dbURL + ";create=true");
                    createTables();
                } catch (SQLException ex) {
                    System.err.println("Could not create placesDB! " + ex.getMessage());
                }
            } else {
                System.err.println("Could not connect to placesDB! " + e.getMessage());
            }
        }
    }

    /**
     * Find all genres from the database
     * @return The JSON element containing all genre entries in the database
     */
    public static JSON getGenres() {

    }

    /**
     * Search for genres, and add them to the database
     */
    public static void addGenres() {
        JSON genres = Search.genres();


    }

    /**
     * Checks if a given genre is stored in the database
     * @param genreIndex The genre to search for
     * @return True if the genre is in the db, false if not stored
     */
    public static boolean findGenre(int genreIndex) {
        JSON json = getGenres();

        return (json.getValue(String.valueOf(genreIndex)) != null);
    }

    /**
     * Create the necessary tables
     */
    private void createTables() {
        createActorTable();
        createMovieTable();
        createGenreTable();
    }

    /**
     * Creates the genres table in the database
     */
    private void createGenreTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.execute("CREATE TABLE genres (" +
                    "id int NOT NULL," +
                    "name varchar(32) NOT NULL)");
            stmt.execute("CREATE UNIQUE INDEX idx on genres (id)");
        } catch (SQLException e) {
            System.err.println("Could not create genres table in database! " + e.getMessage());
        }
    }

    /**
     * Create the movies table in the database
     */
    private void createMovieTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.execute("CREATE TABLE movies (" +
                    "id int NOT NULL," +
                    "title varchar(255) NOT NULL," +
                    "release_data varchar(255) NOT NULL," +
                    "overview varchar(255) NOT NULL," +
                    "vote_average float NOT NULL," +
                    "popularity float NOT NULL," +
                    "adult BIT NOT NULL)");
            stmt.execute("CREATE UNIQUE INDEX idx on movies (id)");
        } catch (SQLException e) {
            System.err.println("Could not create movies table in database! " + e.getMessage());
        }
    }

    /**
     * Create the actors table in the database
     */
    private void createActorTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.execute("CREATE TABLE actors (" +
                    "id int NOT NULL," +
                    "name varchar(255) NOT NULL," +
                    "gender varchar(16) NOT NULL)");
            stmt.execute("CREATE UNIQUE INDEX idx on actors (id)");
        } catch (SQLException e) {
            System.err.println("Could not create actors table in database! " + e.getMessage());
        }
    }
}

package movieExplorer;

import java.sql.*;

public class Database {
    private static Connection con;
    private static Database db = null;

    /**
     * Creates a derby database on disk
     */
    public Database() {
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
    private static JSON getGenres() {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM genres");
            return JSON.convert(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Search for genres, and add them to the database
     */
    public static void addGenres() {
        JSON genres = Search.genres();

        for (int i = 0; i < genres.size(); i++) {
            // TODO: genre.size() er her 0 med testGenres, sjekk JSON strukturen
            JSON genre = genres.get(i);

            String genreValues = (String) genre.getValue(String.valueOf(0));


        }


    }

    /**
     * Fetches a given genre from the database
     * @param genreIndex The index of the genre to fetch
     * @return A JSON object with the genre, or an empty JSON-object if it does not exist
     */
    public static JSON getGenre(int genreIndex) {
        new Database();
        JSON json = getGenres();

        if (json != null) {
            return json.get(String.valueOf(genreIndex));
        }

        return null;
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
            stmt.execute("CREATE UNIQUE INDEX genreindex on genres (id)");
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
                    "popularity float NOT NULL)");
            stmt.execute("CREATE UNIQUE INDEX movieindex on movies (id)");
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
            stmt.execute("CREATE UNIQUE INDEX actorindex on actors (id)");
        } catch (SQLException e) {
            System.err.println("Could not create actors table in database! " + e.getMessage());
        }
    }
}

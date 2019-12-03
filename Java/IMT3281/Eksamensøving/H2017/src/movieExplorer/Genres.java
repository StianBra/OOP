package movieExplorer;

public class Genres {
    public static String resolve(int genreIndex) {
        JSON json = Database.getGenre(genreIndex);

        if (json == null) {
            Database.addGenres();
            json = Database.getGenre(genreIndex);
        }

        return (String) json.getValue("NAME");
    }
}

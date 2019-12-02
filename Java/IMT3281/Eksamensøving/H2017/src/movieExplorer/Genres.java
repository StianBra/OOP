package movieExplorer;

public class Genres {
    public static String resolve(int genreIndex) {
        if (!genreInDatabase(genreIndex)) {
            Database.addGenres();
        }

        JSON json = Database.getGenres();
    }

    private static boolean genreInDatabase(int genreIndex) {
        return Database.findGenre(genreIndex);
    }
}

package no.ntnu.imt3281.S2018;

/**
 * Class containing all logic for the game of life
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 */
public class GameOfLife {

    // The GameOfLife-board containing alive/dead (true/false) cells
    private boolean[][] grid;

    /**
     * Constructor for GameOfLife, sets up a 2D boolean array with the game, and sets all cells to false (dead)
     * @param rows Amount of rows to create
     * @param columns Amount of columns to create
     */
    public GameOfLife(int rows, int columns) {
        grid = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = false;
            }
        }
    }
}

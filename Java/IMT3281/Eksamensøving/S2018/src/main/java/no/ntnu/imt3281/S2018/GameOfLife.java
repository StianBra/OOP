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

        // Sets all cells on the grid to false (dead)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = false;
            }
        }
    }

    /**
     * Checks how many neighbours a given cell [row, column] has
     * (the amount of cells in a 3x3 grid centered around the given [row, column] cell that are true)
     * @param row The row for the cell to check
     * @param column The column for the cell to check
     * @return 0 if no neighbours, 1-8 if some neighbours
     */
    public int amountOfNeighbours(int row, int column) {
        // The amount of neighbours found
        int neighbours = 0;

        // Checks all cells in a 3x3 grid centered around the given point [row, column]
        for (int i = row - 1; i <= row + 2; i++) {
            for (int j = column - 1; j <= column + 2; j++) {
                // Takes care not to count the cell itself
                if ((i != row || j != column) && grid[i][j]) {
                    // If the cell is alive, then increase the amount of neighbours
                    neighbours++;
                }
            }
        }

        // Returns the amount of neighbours
        return neighbours;
    }
}

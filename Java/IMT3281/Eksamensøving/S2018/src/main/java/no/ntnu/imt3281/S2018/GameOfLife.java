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
                // Takes care not to count the cell itself, and also checks that the cell being checked is not ...
                // ... out of bounds
                if ((i != row || j != column) && i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j]) {
                    // The cell is alive, is not the one we're checking against, and it's not out of bounds,
                    // so we increase the amount of neighbours found
                    neighbours++;
                }
            }
        }

        // Returns the amount of neighbours
        return neighbours;
    }

    /**
     * Sets a given cell [row, column] as living (true)
     * @param row The row for the cell to set as living
     * @param column The column for the cell to set as living
     */
    public void setLivingCell(int row, int column) {
        grid[row][column] = true;
    }

    /**
     * Generates the next state for the game of life-board, according to the rules
     */
    public void generateNextState() {
        // Kills all cells that only have two neighbours
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // If the cell is alive at the moment
                if (isAlive(i, j)) {
                    // And it has less than two neighbours
                    if (amountOfNeighbours(i, j) < 2) {
                        // Then we have to kill it
                        grid[i][j] = false;
                    }
                }
            }
        }
    }

    /**
     * Checks whether a given cell [row, column] is alive or not
     * @param row The row for the cell
     * @param column The column for the cell
     * @return True if the cell is alive, false if dead
     */
    public boolean isAlive(int row, int column) {
        return grid[row][column];
    }
}

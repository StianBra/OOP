package no.ntnu.imt3281.S2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private static final int ROWSIZE = 4;
    private static final int COLUMNSIZE = 8;

    /**
     * Creates an empty board, and checks that [1, 4] has no neighbours
     */
    @Test
    void testEmptyBoard() {
        // Creates an empty board
        GameOfLife game = new GameOfLife(ROWSIZE, COLUMNSIZE);

        // Checks that [1, 4] has 0 neighbours
        assertEquals(0, game.amountOfNeighbours(1, 4));
    }
}

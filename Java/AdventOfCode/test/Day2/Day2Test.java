package Day2;

import Day2.Day2Part1;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Day2Test {
    // ArrayList containing numbers representing intcodes
    ArrayList<Integer> intcode = new ArrayList<>();

    /**
     * 1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2)
     */
    @Test
    public void testAddition() {
        // Sets up the intcode 1, 0, 0, 0, 99
        intcode.add(1);
        intcode.add(0);
        intcode.add(0);
        intcode.add(0);
        intcode.add(99);

        // Performs intcode program
        Day2Part1.performIntcode(intcode);

        // Checks that the output has become 2, 0, 0, 0, 99 (addition of 1 + 1, outputted in [0])
        assertEquals(Integer.valueOf(2), intcode.get(0));
    }

    /**
     * 2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
     */
    @Test
    public void testMultiplication() {
        // Sets up the intcode 2, 3, 0, 3, 99
        intcode.add(2);
        intcode.add(3);
        intcode.add(0);
        intcode.add(3);
        intcode.add(99);

        // Performs intcode program
        Day2Part1.performIntcode(intcode);

        // Checks that the output has become 2, 3, 0, 6, 99 (multiplication of 2 * 3, outputted in [3])
        assertEquals(Integer.valueOf(6), intcode.get(3));
    }

    /**
     * 2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
     */
    @Test
    public void testLargeMultiplication() {
        // Sets up the intcode 2, 4, 4, 5, 99, 0
        intcode.add(2);
        intcode.add(4);
        intcode.add(4);
        intcode.add(5);
        intcode.add(99);
        intcode.add(0);

        // Performs intcode program
        Day2Part1.performIntcode(intcode);

        // Checks that the output has become 2, 4, 4, 5, 99, 9801 (multiplication of 99 * 99, outputted in [5])
        assertEquals(Integer.valueOf(9801), intcode.get(5));
    }

    /**
     * 1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
     */
    @Test
    public void testMultipleOperations() {
        // Sets up the intcode 1, 1, 1, 4, 99, 5, 6, 0, 99
        intcode.add(1);
        intcode.add(1);
        intcode.add(1);
        intcode.add(4);
        intcode.add(99);
        intcode.add(5);
        intcode.add(6);
        intcode.add(0);
        intcode.add(99);

        // Performs intcode program
        Day2Part1.performIntcode(intcode);

        // Checks that the output has become 30, 1, 1, 4, 2, 5, 6, 0, 99
        // (Addition of 1 + 1 outputted in [4] and multiplication of 5 * 6 outputted in [0]
        assertEquals(Integer.valueOf(30), intcode.get(0));
        assertEquals(Integer.valueOf(2), intcode.get(4));
    }
}

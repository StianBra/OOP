package Day4;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Day4Test {
    // Checks that 111111 is a valid password (double 11's, it does not decrease in value from left -> right)
    @Test
    public void testPasswordValidityCase1() {
        assertTrue(Day4Part1.validPassword(111111));
    }

    // Checks that 223450 is an invalid password (decreasing value from 5 -> 0)
    @Test
    public void testPasswordValidityCase2() {
        assertFalse(Day4Part1.validPassword(223450));
    }

    // Checks that 123789 is an invalid password (no pair of numbers adjacent to each other that are the same)
    @Test
    public void testPasswordValidityCase3() {
        assertFalse(Day4Part1.validPassword(123789));
    }
}

package Day1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {
    @Test
    public void testPart2() {
        // Test case 1, mass of 14 should only require 2 fuel
        assertEquals(2, Day1Part2.calculateFuel(14));
        // Should not recursively add more fuel
        assertEquals(0, Day1Part2.calculateFuel(Day1Part2.calculateFuel(14)));

        // Test case 2, mass of 1969 should require 654 + 216 + 70 + 21 + 5 = 966 fuel
        assertEquals(966, Day1Part2.calculateFuel(1969));
        // Should add 216 + 70 + 21 + 5 = 312 more fuel recursively after finding 654 fuel
        assertEquals(312, Day1Part2.calculateFuel(654));
        // Should add 102 + 32 + 8 = 142 more fuel recursively after finding 312 fuel
        assertEquals(142, Day1Part2.calculateFuel(312));
        // Should add 21 + 5 = 26 more fuel recursively after finding 96 fuel
        assertEquals(21, Day1Part2.calculateFuel(96));
        // Should add 5 more fuel after finding 21 fuel
        assertEquals(5, Day1Part2.calculateFuel(21));

        // Test case 3, mass of 100756 should require 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346 fuel
        assertEquals(50346, Day1Part2.calculateFuel(100756));
    }
}

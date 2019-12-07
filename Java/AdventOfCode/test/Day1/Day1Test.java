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
        // Should add 45 + 13 + 2 = 60 more fuel after finding 142 fuel
        assertEquals(60, Day1Part2.calculateFuel(142));
        // Should add 18 + 4 = 22 more fuel after finding 60 fuel
        assertEquals(22, Day1Part2.calculateFuel(60));
        // Should add 5 more fuel after finding 22 fuel
        assertEquals(5, Day1Part2.calculateFuel(22));
        // Should not add any more fuel after finding 5 fuel
        assertEquals(0, Day1Part2.calculateFuel(5));

        // Test case 3, mass of 100756 should require 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346 fuel
        assertEquals(50346, Day1Part2.calculateFuel(100756));
        // Should add 16780 + 5591 + 1861 + 618 + 204 + 66 + 20 + 4 = 25144 more fuel recursively after finding 50346 fuel
        assertEquals(25144, Day1Part2.calculateFuel(50346));
        // Should add 8379 + 2791 + 928 + 307 + 100 + 31 + 8 = 12544 more fuel recursively after finding 25144 fuel
        assertEquals(12544, Day1Part2.calculateFuel(25144));
        // Should add 4179 + 1391 + 461 + 151 + 48 + 14 + 2 = 6246 more fuel recursively after finding 12544 fuel
        assertEquals(6246, Day1Part2.calculateFuel(12544));
        // Should add 2080 + 691 + 228 + 74 + 22 + 5 = 3100 more fuel recursively after finding 6246 fuel
        assertEquals(3100, Day1Part2.calculateFuel(6246));
        // Should add 1031 + 341 + 111 + 35 + 9 + 1 = 1528 more fuel recursively after finding 3100 fuel
        assertEquals(1528, Day1Part2.calculateFuel(3100));
        // Should add 507 + 167 + 53 + 15 + 3 = 745 more fuel recursively after finding 1528 fuel
        assertEquals(745, Day1Part2.calculateFuel(1528));
        // Should add 246 + 80 + 24 + 6 = 356 more fuel recursively after finding 745 fuel
        assertEquals(356, Day1Part2.calculateFuel(745));
        // Should add 116 + 36 + 10 + 1 = 163 more fuel recursively after finding 356 fuel
        assertEquals(163, Day1Part2.calculateFuel(356));
        // Should add 52 + 15 + 3 = 70 more fuel recursively after finding 163 fuel
        assertEquals(70, Day1Part2.calculateFuel(163));
        // Should add 21 + 5 = 26 more fuel recursively after finding 70 fuel
        assertEquals(26, Day1Part2.calculateFuel(70));
        // Should add 6 more fuel recursively after finding 26 fuel
        assertEquals(6, Day1Part2.calculateFuel(26));
        // Should not add more fuel after finding 6 fuel
        assertEquals(0, Day1Part2.calculateFuel(6));

        // Tests special numbers
        assertEquals(0, Day1Part2.calculateFuel(-12345));
        assertEquals(0, Day1Part2.calculateFuel(-1));
        assertEquals(0, Day1Part2.calculateFuel(0));
    }
}

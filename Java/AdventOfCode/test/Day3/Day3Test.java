package Day3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    /**
     * Checks that R75,D30,R83,U83,L12,D49,R71,U7,L72
     * U62,R66,U55,R34,D71,R55,D58,R83 = distance 159
     */
    @Test
    public void testCase1() {
        List<String> listA = new ArrayList<>(Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(",")));
        List<String> listB = new ArrayList<>(Arrays.asList("U62,R66,U55,R34,D71,R55,D58,R83".split(",")));

        boolean[][] gridA = new boolean[Day3Part2.MAXLENGTH][Day3Part2.MAXLENGTH];
        boolean[][] gridB = new boolean[Day3Part2.MAXLENGTH][Day3Part2.MAXLENGTH];

        Day3Part2.registerVisitedPoints(listA, gridA);
        Day3Part2.registerVisitedPoints(listB, gridB);

        assertEquals(159, Day3Part1.findCollisionWithShortestDistance(gridA, gridB));
    }

    /**
     * Checks that R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
     * U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = distance 135
     */
    @Test
    public void testCase2() {
        List<String> listA = new ArrayList<>(Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(",")));
        List<String> listB = new ArrayList<>(Arrays.asList("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(",")));

        boolean[][] gridA = new boolean[Day3Part2.MAXLENGTH][Day3Part2.MAXLENGTH];
        boolean[][] gridB = new boolean[Day3Part2.MAXLENGTH][Day3Part2.MAXLENGTH];

        Day3Part2.registerVisitedPoints(listA, gridA);
        Day3Part2.registerVisitedPoints(listB, gridB);

        assertEquals(135, Day3Part1.findCollisionWithShortestDistance(gridA, gridB));
    }
}

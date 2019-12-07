package Day3;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for solving https://adventofcode.com/2019/day/3, part 2
 * Two lines of input, with direction/distance moved for each line
 * Find the crossing point of the lines, with the fewest amount of steps taken to get there combined for both lines
 */
public class Day3Part2 {
    static final int MAXLENGTH = 50000;

    public static void main(String[] args) {
        List<List<String>> lines = readInput();

        List<String> lineA = lines.remove(0);
        List<String> lineB = lines.remove(0);

        boolean[][] gridA = new boolean[MAXLENGTH][MAXLENGTH];
        boolean[][] gridB = new boolean[MAXLENGTH][MAXLENGTH];

        // Mark each visited point as true in the boolean[][] arrays above
        registerVisitedPoints(lineA, gridA);
        registerVisitedPoints(lineB, gridB);

        // Find every x/y coordinate that corresponds to an intersection between the lines
        List<Pair<Integer, Integer>> coordinates = findIntersections(gridA, gridB);

        int minimumDistance = MAXLENGTH*2;

        // Find the intersection which requires the fewest amount of steps from origin
        for (Pair<Integer, Integer> coordinate : coordinates) {
            int steps = findAmountOfSteps(coordinate.getKey(), coordinate.getValue(), lineA, lineB);
            if (steps < minimumDistance) {
                minimumDistance = steps;
            }
        }

        System.out.println("Minimum distance x + y = " + minimumDistance);
    }

    /**
     * Finds every x/y coordinate that corresponds to an intersection based on two boolean[][] arrays
     * @param gridA The first boolean[][] array, represents each point that has been visited by the first line
     * @param gridB The second boolean[][] array, represents each point that has been visited by the second line
     * @return A List of Pairs with x/y coordinates
     */
    private static List<Pair<Integer, Integer>> findIntersections(boolean[][] gridA, boolean[][] gridB) {
        ArrayList<Pair<Integer, Integer>> coordinates = new ArrayList<>();

        for (int i = 0; i < gridA.length; i++) {
            for (int j = 0; j < gridB.length; j++) {
                if (createdIntersection(i, j, gridA, gridB)) {
                    coordinates.add(new Pair<>(i, j));
                }
            }
        }

        return coordinates;
    }

    /**
     * Marks every point a line has visited
     * @param list The list of each direction/distance to travel for the line
     * @param grid The array used to store which points have been visited
     */
    protected static void registerVisitedPoints(List<String> list, boolean[][] grid) {
        // Defines starting x/y coordinates for the line, with origin in the centre [MAXLENGTH / 2, MAXLENGTH / 2]
        int currentXPosition = MAXLENGTH / 2;
        int currentYPosition = MAXLENGTH / 2;

        for (String line : list) {
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            switch (direction) {
                // UP
                case 'U':
                    System.out.println("Moving UP from [" + currentXPosition + "][" + currentYPosition + "]");
                    for (int i = currentYPosition - 1; i >= currentYPosition - distance; i--) {
                        grid[currentXPosition][i] = true;
                    }

                    currentYPosition -= distance;
                    break;

                // DOWN
                case 'D':
                    System.out.println("Moving DOWN from [" + currentXPosition + "][" + currentYPosition + "]");
                    for (int i = currentYPosition + 1; i <= currentYPosition + distance; i++) {
                        grid[currentXPosition][i] = true;
                    }

                    currentYPosition += distance;
                    break;

                // LEFT
                case 'L':
                    System.out.println("Moving LEFT from [" + currentXPosition + "][" + currentYPosition + "]");
                    for (int i = currentXPosition - 1; i >= currentXPosition - distance; i--) {
                        grid[i][currentYPosition] = true;
                    }

                    currentXPosition -= distance;
                    break;

                // RIGHT
                case 'R':
                    System.out.println("Moving RIGHT from [" + currentXPosition + "][" + currentYPosition + "]");
                    for (int i = currentXPosition + 1; i <= currentXPosition + distance; i++) {
                        grid[i][currentYPosition] = true;
                    }

                    currentXPosition += distance;
                    break;

                default:
                    System.err.println(",\tInvalid direction, got: " + direction);
                    break;
            }
        }
    }

    /**
     * Finds the amount of steps taken to get to a point, for both lines
     * @param x The X coordinate of the point to locate
     * @param y The Y coordinate of the point to locate
     * @param listA The list of directions/distances to travel, for line A
     * @param listB The list of directions/distances to travel, for line B
     * @return Int, amount of steps taken for both lines combined.
     */
    private static int findAmountOfSteps(int x, int y, List<String> listA, List<String> listB) {
        Pair<Integer, Integer> steps = new Pair<>(findStepsToPoint(listA, x, y), findStepsToPoint(listB, x, y));

        System.out.println("Found " + x + ", " + y + " for line A after " + steps.getKey() + " steps.");
        System.out.println("Found " + x + ", " + y + " for line B after " + steps.getValue() + " steps.");
        System.out.println("Total steps: " + steps.getKey() + " + " + steps.getValue() + " = " + (steps.getKey()+steps.getValue()));

        return steps.getKey() + steps.getValue();
    }

    /**
     * Find the amount of steps taken to get to a point (x/y)
     * @param list The list of directions/distances to travel
     * @param x The X coordinate of the point to locate
     * @param y The Y coordinate of the point to locate
     * @return Int, amount of steps taken. MAXLENGTH if the point is not visited at all
     */
    private static int findStepsToPoint(List<String> list, int x, int y) {
        int totalDistance = 0;
        int currentXPosition = MAXLENGTH / 2;
        int currentYPosition = MAXLENGTH / 2;

        for (String line : list) {
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            switch (direction) {
                // UP
                case 'U':
                    for (int i = currentYPosition - 1; i >= currentYPosition - distance; i--) {
                        totalDistance++;

                        if (currentXPosition == x && i == y) {
                            return totalDistance;
                        }
                    }

                    currentYPosition -= distance;
                    break;

                // DOWN
                case 'D':
                    for (int i = currentYPosition + 1; i <= currentYPosition + distance; i++) {
                        totalDistance++;

                        if (currentXPosition == x && i == y) {
                            return totalDistance;
                        }
                    }

                    currentYPosition += distance;
                    break;

                // LEFT
                case 'L':
                    for (int i = currentXPosition - 1; i >= currentXPosition - distance; i--) {
                        totalDistance++;

                        if (i == x && currentYPosition == y) {
                            return totalDistance;
                        }
                    }

                    currentXPosition -= distance;
                    break;

                // RIGHT
                case 'R':
                    for (int i = currentXPosition + 1; i <= currentXPosition + distance; i++) {
                        totalDistance++;

                        if (i == x && currentYPosition == y) {
                            return totalDistance;
                        }
                    }

                    currentXPosition += distance;
                    break;
                default:
                    System.err.println(",\tInvalid direction, got: " + direction);
                    break;
            }
        }

        // Did not find x/y as an intersection
        return MAXLENGTH;
    }

    /**
     * Checks whether a coordinate (x, y) has been visited by two different lines
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param gridA The boolean[][] array for line A, every visited cell/coordinate is true
     * @param gridB The boolean[][] array for line B, every visited cell/coordinate is true
     * @return True if there is an intersection (both line A and B has visited the coordinate), false if not
     */
    private static boolean createdIntersection(int x, int y, boolean[][] gridA, boolean[][] gridB) {
        // Checks every point except the origin (MAXLENGTH/2)
        return (gridA[x][y] && gridA[x][y] == gridB[x][y] && (x != MAXLENGTH / 2 || y != MAXLENGTH / 2));
    }

    /**
     * Reads a line of comma-separated strings from file
     * @return An arraylist of the strings read from file
     */
    private static List<List<String>> readInput() {
        String lineA = "";
        String lineB = "";

        // Get the line from file
        try (BufferedReader in = new BufferedReader(new FileReader("src/Day3/input.txt"))) {
            lineA = in.readLine();
            lineB = in.readLine();
        } catch (IOException e) {
            System.err.println("Encountered an IO exception while reading from input.txt! " + e.getMessage());
        }

        // Separate each direction/number by comma
        List<List<String>> lines = new ArrayList<>();
        lines.add(Arrays.asList(lineA.split(",")));
        lines.add(Arrays.asList(lineB.split(",")));

        return lines;
    }
}

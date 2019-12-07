package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for solving https://adventofcode.com/2019/day/3, part 1
 * Two lines of input, with direction/distance moved for each line
 * Find the crossing point of the lines, closest to origin as measured by manhattan distance
 */
public class Day3Part1 {
    static final int MAXLENGTH = 50000;

    public static void main(String[] args) {
        List<List<String>> lines = readInput();

        List<String> lineA = lines.remove(0);
        List<String> lineB = lines.remove(0);

        boolean[][] gridA = new boolean[MAXLENGTH][MAXLENGTH];
        boolean[][] gridB = new boolean[MAXLENGTH][MAXLENGTH];

        // Defines starting x/y coordinates for A and B lines, with origin in the centre [MAXLENGTH / 2, MAXLENGTH / 2]
        int currentXPositionA = MAXLENGTH / 2;
        int currentYPositionA = MAXLENGTH / 2;

        int currentXPositionB = MAXLENGTH / 2;
        int currentYPositionB = MAXLENGTH / 2;

        for (String line : lineA) {
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            System.out.println("Distance to move: " + distance);

            switch (direction) {
                // UP
                case 'U':
                    System.out.println("Moving UP from [" + currentXPositionA + "][" + currentYPositionA + "]");
                    for (int i = currentYPositionA - 1; i >= currentYPositionA - distance; i--) {
                        gridA[currentXPositionA][i] = true;
                    }

                    currentYPositionA -= distance;
                    break;

                // DOWN
                case 'D':
                    System.out.println("Moving DOWN from [" + currentXPositionA + "][" + currentYPositionA + "]");
                    for (int i = currentYPositionA + 1; i <= currentYPositionA + distance; i++) {
                        gridA[currentXPositionA][i] = true;
                    }

                    currentYPositionA += distance;
                    break;

                // LEFT
                case 'L':
                    System.out.println("Moving LEFT from [" + currentXPositionA + "][" + currentYPositionA + "]");
                    for (int i = currentXPositionA - 1; i >= currentXPositionA - distance; i--) {
                        gridA[i][currentYPositionA] = true;
                    }

                    currentXPositionA -= distance;
                    break;

                // RIGHT
                case 'R':
                    System.out.println("Moving RIGHT from [" + currentXPositionA + "][" + currentYPositionA + "]");
                    for (int i = currentXPositionA + 1; i <= currentXPositionA + distance; i++) {
                        gridA[i][currentYPositionA] = true;
                    }

                    currentXPositionA += distance;
                    break;
                default:
                    System.err.println(",\tInvalid direction, got: " + direction);
                    break;
            }

            System.out.println("Moved to [" + currentXPositionA + "][" + currentYPositionA + "]\n");
        }

        for (String line : lineB) {
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            System.out.println("Distance to move: " + distance);

            switch (direction) {
                // UP
                case 'U':
                    System.out.println("Moving UP from [" + currentXPositionB + "][" + currentYPositionB + "]");
                    for (int i = currentYPositionB - 1; i >= currentYPositionB - distance; i--) {
                        gridB[currentXPositionB][i] = true;
                    }

                    currentYPositionB -= distance;
                    break;

                // DOWN
                case 'D':
                    System.out.println("Moving DOWN from [" + currentXPositionB + "][" + currentYPositionB + "]");
                    for (int i = currentYPositionB + 1; i <= currentYPositionB + distance; i++) {
                        gridB[currentXPositionB][i] = true;
                    }

                    currentYPositionB += distance;
                    break;

                // LEFT
                case 'L':
                    System.out.println("Moving LEFT from [" + currentXPositionB + "][" + currentYPositionB + "]");
                    for (int i = currentXPositionB - 1; i >= currentXPositionB - distance; i--) {
                        gridB[i][currentYPositionB] = true;
                    }

                    currentXPositionB -= distance;
                    break;

                // RIGHT
                case 'R':
                    System.out.println("Moving RIGHT from [" + currentXPositionB + "][" + currentYPositionB + "]");
                    for (int i = currentXPositionB + 1; i <= currentXPositionB + distance; i++) {
                        gridB[i][currentYPositionB] = true;
                    }

                    currentXPositionB += distance;
                    break;
                default:
                    System.err.println(",\tInvalid direction, got: " + direction);
                    break;
            }

            System.out.println("Moved to [" + currentXPositionB + "][" + currentYPositionB + "]\n");
        }

        int minimumDistance = findCollisionWithShortestDistance(gridA, gridB);
        System.out.println("Found shortest distance of crossing to origin: " + minimumDistance);
    }

    /**
     * Finds every collision between line A/B, and finds the collision with shortest manhattan distance to origin
     * @param gridA
     * @param gridB
     * @return
     */
    protected static int findCollisionWithShortestDistance(boolean[][] gridA, boolean[][] gridB) {
        int shortestDistance = MAXLENGTH;

        for (int i = 0; i < MAXLENGTH; i++) {
            for (int j = 0; j < MAXLENGTH; j++) {
                // If there is a 'collision', and it's not in origin
                if (gridA[i][j] && gridA[i][j] == gridB[i][j] && (i != MAXLENGTH / 2 || j != MAXLENGTH / 2)) {
                    // Find the distance to origin
                    int newDistance = findManhattanDistance(i, j);
                    if (newDistance < shortestDistance) {
                        shortestDistance = newDistance;
                    }
                }
            }
        }

        return shortestDistance;
    }

    /**
     * Finds the manhattan distance between a point [x][y] and origin [MAXLENGTH / 2][MAXLENGTH / 2] (centre of board)
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     * @return Int, distance between the given x/y point and origin
     */
    private static int findManhattanDistance(int x, int y) {
        int origin = MAXLENGTH / 2;

        return (Math.abs(origin - x) + Math.abs(origin - y));
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

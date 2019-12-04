package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for solving https://adventofcode.com/2019/day/1, part 2
 */
public class Day1Part2 {
    public static void main(String[] args) {
        ArrayList<String> input = (ArrayList<String>) readInput();
        int fuelSum;

        // Test stuff
        System.out.println("Test fuel: " + calculateFuel(1969));

        // Processes every line from the input txt file, and gets the amount of fuel required by the mass of each line
        fuelSum = input.stream().mapToInt(line -> calculateFuel((Integer.parseInt(line) / 3) - 2)).sum();

        System.out.println("Fuel needed: " + fuelSum);
    }

    /**
     * Recursively calculates the amount of fuel needed for a given mass
     * Necessary fuel is calculated as mass / 3 - 2
     * @param mass The mass to calculate required fuel for
     * @return The total amount of fuel necessary for the mass, plus the mass of the required fuel,
     * plus that required fuel's added mass, etc.
     */
    public static int calculateFuel(int mass) {
        int requiredFuel = (mass / 3) - 2;

        if (requiredFuel > 0) {
            requiredFuel += calculateFuel(requiredFuel);
        }

        // Returns either the amount of fuel, or 0 if it was negative
        return Math.max(requiredFuel, 0);
    }

    private static List<String> readInput() {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader("src/Day1/input.txt"))) {
            String temp;

            while ((temp = in.readLine()) != null) {
                lines.add(temp);
            }
        } catch (IOException e) {
            System.err.println("Encountered an IO exception while reading from input.txt! " + e.getMessage());
        }

        return lines;
    }
}

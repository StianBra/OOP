package Day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<String> input = (ArrayList<String>) readInput();

        int fuelSum = 0;
        
        for (String line : input) {
            fuelSum += calculateFuel(line);
        }

        System.out.println("Fuel needed: " + fuelSum);
    }

    private static int calculateFuel(String line) {
        // Get the mass of the current line
        int mass = Integer.parseInt(line);

        // Divide by three rounded down, and subtract two
        return (mass / 3) - 2;
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

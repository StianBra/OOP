package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for solving https://adventofcode.com/2019/day/2, part 1
 */
public class Day2Part1 {
    public static void main(String[] args) {
        // Retrieves the numbers from input.txt
        ArrayList<Integer> numbers = (ArrayList<Integer>) readInput();

        // Restores the intcode program state to the 1202 program alarm
        restoreGravityAssistProgram(numbers);

        // Performs the intcode program based on the content of the numbers arraylist
        performIntcode(numbers);

        // Checks what the contents of position 0 is
        System.out.println("Position 0 after halting: " + numbers.get(0));
    }

    /**
     * Before running the program, replace position 1 with the value 12 and replace position 2 with the value 2.
     * @param numbers The arraylist of intcodes to modify
     */
    private static void restoreGravityAssistProgram(List<Integer> numbers) {
        numbers.set(1, 12);
        numbers.set(2, 2);
    }

    /**
     * Performs an intcode program based on an arraylist of numbers
     * @param numbers The arraylist of intcodes
     */
    public static void performIntcode(List<Integer> numbers) {
        // Current index of 4-number sets in the input
        int index = 0;

        // Stops program if opcode 99 (HALT) is encountered
        boolean halting = false;

        // Gets the size of the input
        int size = numbers.size();

        // Loops as long as we're not halting (opcode 99) and have not reached the end of input yet
        while (!halting && index < size) {
            int resultIndex = 0;
            int xNum = 0;
            int yNum = 0;

            // First number is the opcode, check which one
            int opcode = numbers.get(index);

            // Checks whether a calculation should be performed
            if (opcode == 1 || opcode == 2) {
                // Gets the numbers to multiply/add (index 1 and 2), and where to output the result (index 3)
                xNum = numbers.get(numbers.get(index + 1));
                yNum = numbers.get(numbers.get(index + 2));
                resultIndex = numbers.get(index + 3);
            }

            switch (opcode) {
                // Addition
                case 1:
                    numbers.set(resultIndex, xNum + yNum);
                    break;

                // Multiplication
                case 2:
                    numbers.set(resultIndex, xNum * yNum);
                    break;

                // HALT
                case 99:
                    halting = true;
                    break;

                // Default
                default:
                    System.err.println("Unknown opcode: " + numbers.get(index));
                    break;
            }

            // Parses 4 numbers at a time
            index += 4;
        }
    }

    /**
     * Reads a line of comma-separated integers from file
     * @return An arraylist of the integers read from file
     */
    private static List<Integer> readInput() {
        String line = "";

        // Get the line from file
        try (BufferedReader in = new BufferedReader(new FileReader("src/Day2/input.txt"))) {
            line = in.readLine();
        } catch (IOException e) {
            System.err.println("Encountered an IO exception while reading from input.txt! " + e.getMessage());
        }

        // Separate each number by comma
        String[] commaSeparated = line.split(",");

        // Retrieve every number and add them to the numbers arraylist, and then return this list
        return Arrays.stream(commaSeparated).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
    }
}

package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for solving https://adventofcode.com/2019/day/2, part 2
 */
public class Day2Part2 {
    public static void main(String[] args) {
        boolean foundNumbers = false;
        int noun = 0;
        int verb = 0;

        // Iterates through possible noun/verb values to find the number 19690720 as result of the intcode program
        while (!foundNumbers && (noun <= 99 || verb <= 99)) {
            // Retrieves the numbers from input.txt
            ArrayList<Integer> numbers = (ArrayList<Integer>) readInput();

            // Changes value 1 and 2 to our current noun/verb
            changeNounVerb(numbers, noun, verb);

            // Performs the intcode program based on the content of the numbers arraylist
            performIntcode(numbers);

            // Checks if the contents of position 0 is the value we're looking for
            if (numbers.get(0) == 19690720) {
                foundNumbers = true;
                System.out.print("\n\n");
                System.out.println("FOUND CORRECT VALUE! ");
                System.out.println("Noun = " + noun);
                System.out.println("Verb = " + verb);
                System.out.println("100 * NOUN + VERB = " + (100 * noun + verb));
                System.out.print("\n\n");
            }

            // Increments noun/verb
            if (verb++ == 100) {
                verb = 0;
                noun++;
            }
        }
    }

    /**
     * Before running the program, replace position 1 with the value of noun and replace position 2 with the value of verb.
     * @param numbers The arraylist of intcodes to modify
     */
    private static void changeNounVerb(List<Integer> numbers, int noun, int verb) {
        numbers.set(1, noun);
        numbers.set(2, verb);
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
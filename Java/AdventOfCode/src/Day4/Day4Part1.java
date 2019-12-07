package Day4;

/**
 * Class for solving https://adventofcode.com/2019/day/4, part 1
 * Two numbers as input, find every valid password between these two numbers
 * - Six-digit number
 * - Two of the digits adjacent to eachother must be the same (like 22 in 122345)
 * - From left -> right digits cannot decrease (111123 or 135679 is invalid)
 */
public class Day4Part1 {
    public static void main(String[] args) {
        int startRange = 278384;
        int endRange = 824795;

        int validPasswords = 0;

        // Find the amount of valid passwords between startRange and endRange
        for (int i = startRange + 1; i < endRange; i++) {
            if (validPassword(i)) {
                System.out.println("Found new valid password: " + i);
                validPasswords++;
            }
        }

        System.out.println("Amount of valid passwords: " + validPasswords);
    }

    /**
     * Checks if a given password is valid:
     * - Six-digit number
     * - Two of the digits adjacent to eachother must be the same (like 22 in 122345)
     * - From left -> right digits cannot decrease (111123 or 135679 is invalid)
     * @param password The password-number to check
     * @return True if valid password, false if invalid
     */
    public static boolean validPassword(int password) {
        // Performs all checks, return true if they are all true, and false (invalid password) if at least one check fails
        return password % 1000000 == password && doubleDigits(password) && !decreasingValue(password);
    }

    /**
     * Checks whether a password has numbers in decreasing value from left -> right or not
     * @param password The password to check
     * @return True if password has decreasing values from left -> right (invalid password), false if not
     */
    private static boolean decreasingValue(int password) {
        // Convert the int to a string
        String pw = String.valueOf(password);

        // Iterate through each character in the string
        for (int i = 0; i < pw.length() - 1; i++) {
            // Get the current char
            char ch = pw.charAt(i);

            // If the char is greater than the next one, then the next char must be a number which is lower in value
            if (ch > pw.charAt(i + 1)) {
                return true;
            }
        }

        // None of the numbers to the right were greater than the ones on the left, password does not have decreasing values
        return false;
    }

    /**
     * Checks whether a password contains two double-digits or not
     * @param password The password to check
     * @return True if the password contains two identical numbers adjacent to each other, false if not
     */
    private static boolean doubleDigits(int password) {
        // Convert the int to a string
        String pw = String.valueOf(password);

        // Iterate through each character in the string
        for (int i = 0; i < pw.length() - 1; i++) {
            // Get the current char
            char ch = pw.charAt(i);

            // If the char is the same as the next one in the string, return true (has double digits)
            if (ch == pw.charAt(i + 1)) {
                return true;
            }
        }

        // None of the numbers were the same as their adjacents, does not have double digits.
        return false;
    }
}

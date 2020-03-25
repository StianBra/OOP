using System;
using System.Collections.Generic;
using System.Text;

/* https://projecteuler.net/problem=1
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000. */

namespace Project_Euler.Problem1 {
    class Problem1 {
        /// <summary>
        /// Finds the sum of all the multiples of 3 or 5, for every natural number below <paramref name="num"/>
        /// </summary>
        /// <param name="num">Acts as an upper limit for which numbers to find multiples of 3 or 5 for.</param>
        /// <returns>The sum of multiples of 3 or 5.</returns>
        public static int SolveProblem(int num)
        {
            int sumOfMultiples = 0;

            // First add all multiples of 3:
            for (int i = 3; i < num; i += 3)
            {
                sumOfMultiples += i;
            }

            // Then add all multiples of 5:
            for (int i = 5; i < num; i += 5)
            {
                sumOfMultiples += i;
            }

            // Need to remove the numbers that are a multiple of *both* 5 and 3, since numbers should only occur once
            // This can be solved by subtracting multiples of gcd(5, 3) -> subtract multiples of 15:
            for (int i = 15; i < num; i += 15)
            {
                sumOfMultiples -= i;
            }

            return sumOfMultiples;
        }
    }
}
using System;
using System.Collections.Generic;
using System.Text;
using NUnit.Framework;
using Project_Euler.Problem1;

namespace Project_Euler.Problem1.Tests {
    internal class Problem1 {
        [TestFixture]
        public class TestProblem1
        {
            [Test]
            public void TestNumbersUnder10()
            {
                int multipleSum = Project_Euler.Problem1.Problem1.SolveProblem(10);

                Assert.AreEqual(23, multipleSum);
            }

            [Test]
            public void TestNumbersUnder100() {
                int multipleSum = Project_Euler.Problem1.Problem1.SolveProblem(100);

                Assert.AreEqual(2318, multipleSum);
            }

            [Test]
            public void TestNumbersUnder1000() {
                int multipleSum = Project_Euler.Problem1.Problem1.SolveProblem(1000);

                Assert.AreEqual(233168, multipleSum);
            }
        }
    }
}

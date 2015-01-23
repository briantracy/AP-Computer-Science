package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

        /**
         *  THESE TEST CASES WILL BE PERFORMED THROUGH THE USE OF `assert`
         *  TO ENABLE ASSERTIONS IN THE JVM, PASS THE `-ea` FLAG FOR `enable assertions`.
         *  TO ENSURE THAT ASSERTIONS ARE PROPERLY WORKING, THE NEXT LINE CONTAINS A FALSE
         *  ASSERTION THAT SHOULD CAUSE THE PROGRAM TO TERMINATE. IF THIS IS THE CASE, REMOVE
         *  THE OFFENDING ASSERTION TO SEE THE REST OF THE TEST CASES.
         */

        //assert false : "Asserts are working properly"; // should crash if you have assertions on. included to ensure the veracity of all other assertions

        /**
         *      HERE LIE SEVERAL TEST CASES
         *      THE FIRST SET ARE COMPUTED
         *      THE SECOND SET ARE AGAINST CONSTANTS FOUND ON THE INTERNET
         */



        /**
         *
         *  First set of test cases; These tests are within the computable bounds for Math.pow()
         *  These test are in the range of [0-10) ^ [0-10)
         */

        for (int testBase = 0; testBase < 10; testBase++)
        {
            String base = Integer.toString(testBase);

            for (int testExponent = 0; testExponent < 10; testExponent++)
            {
                int computed = (int)Math.pow(testBase,testExponent);
                BigInteger testCase = exponentiationBySuccessiveSquaring(new BigInteger(base), testExponent);

                assert testCase.intValue() == computed;

            }
        }

        /** Second set of test cases; These numbers will be outside of the computable bound of Math.pow()
         *  therefore that function cannot be used to check the results. The following powers of 2 are
         *  found on the internet and will be used as the test cases in part two.
         *
         *  See <link>http://en.wikipedia.org/wiki/Power_of_two</link> to obtain the list used.
         */

        BigInteger two          = new BigInteger("2");
        BigInteger twoToThe20   = new BigInteger("1048576");
        BigInteger twoToThe25   = new BigInteger("33554432");
        BigInteger twoToThe30   = new BigInteger("1073741824");
        BigInteger twoToThe32   = new BigInteger("4294967296");
        BigInteger twoToThe40   = new BigInteger("1099511627776");
        BigInteger twoToThe50   = new BigInteger("1125899906842624");
        BigInteger twoToThe64   = new BigInteger("18446744073709551616");
        BigInteger twoToThe95   = new BigInteger("39614081257132168796771975168");
        BigInteger twoToThe512  = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096");

        assert exponentiationBySuccessiveSquaring(two, 20).equals(twoToThe20);

        assert exponentiationBySuccessiveSquaring(two, 25).equals(twoToThe25);

        assert exponentiationBySuccessiveSquaring(two, 30).equals(twoToThe30);

        assert exponentiationBySuccessiveSquaring(two, 32).equals(twoToThe32);

        assert exponentiationBySuccessiveSquaring(two, 40).equals(twoToThe40);

        assert exponentiationBySuccessiveSquaring(two, 50).equals(twoToThe50);

        assert exponentiationBySuccessiveSquaring(two, 64).equals(twoToThe64);

        assert exponentiationBySuccessiveSquaring(two, 95).equals(twoToThe95);

        assert exponentiationBySuccessiveSquaring(two,512).equals(twoToThe512);

        System.out.println("All Assertions True");
    }

    /**
     *
     * @param base The base of the exponent to be found
     * @param exponent The exponent that the base should be raised to.
     * @return The result of base^exponent
     */
    private static BigInteger exponentiationBySuccessiveSquaring(BigInteger base, int exponent)
    {


        /**
         * Base Cases.
         * Any number raised to the power of 1 is that number.
         * Any number raised to the power of 0 is 0.
         */

        if (exponent == 0) {
            return BigInteger.ONE;
        }

        if (exponent == 1) {
            return base;
        }


        if (isEven(exponent)) {
            return exponentiationBySuccessiveSquaring(square(base), exponent/2);
        }

        return base.multiply(exponentiationBySuccessiveSquaring(square(base), (exponent-1)/2));
    }

    /**
     * <summary>
     *     This function determines whether the given number is even. This method was created
     *     to encapsulate `i % 2 == 0` in a more readable way.
     * </summary>
     * @param x The number to be determined even or odd
     * @return Whether or not the given value is even
     */
    private static boolean isEven(int x)
    {
        return x % 2 == 0;
    }

    /**
     * <summary>
     *     This function was created to encapsulate the .multiply() function of BigInteger in a
     *     more readable way. Because BigIntegers cannot be multiplied together with `*`, this function
     *     is necessary.
     * </summary>
     * @param integer the BigInteger to be squared
     * @return the given value to the power of two
     */
    private static BigInteger square(BigInteger integer)
    {
        return integer.multiply(integer);
    }

}

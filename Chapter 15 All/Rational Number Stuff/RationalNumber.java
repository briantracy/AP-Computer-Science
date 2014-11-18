import java.math.BigInteger;


/**
 * This class represents a rational number ie: can be expressed in a / b form.
 * For this reason, there are two instance variables, one for the numerator and one for the denominator
 *
 * This class can never have a denominator of 0 as the setter for denominator will throw an exception if the
 * passed value is 0
 */
public class RationalNumber
{
    /**
     * These are the only instance variables required by the specification. They are two be ints
     */
    private int numerator;
    private int denominator;

    /**
     * Default no argument constructor. Sets the fraction to 0 / 1
     */
    public RationalNumber()
    {
        setNumerator(0);
        setDenominator(1);
    }

    /**
     * This is the more useful constructor that makes a rational number with the given values
     * @param numerator Numerator of the fraction
     * @param denominator denominator of the fraction
     */
    public RationalNumber(int numerator, int denominator)
    {
        setNumerator(numerator);
        setDenominator(denominator);
    }


    /**
     *  Standard Getters
     */
    public int getNumerator  () { return numerator; }
    public int getDenominator() { return denominator; }


    /**
     * Standard setter for numerator as there are no restrictions on what a numerator can be
     * @param numerator The new numerator
     */
    public void setNumerator(int numerator) { this.numerator = numerator; }


    /**
     * Setter for the denominator. This has a check for a denominator of zero, as this is not allowed.
     * Will throw an IllegalArgumentException is passed zero, otherwise will perform as expected.
     * @param denominator The new denominator
     */
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        else {
            this.denominator = denominator;
        }
    }


    /**
     * Object Method for printing a rational number.
     * This method prints the address of the rational number, followed by its numerator and denominator
     *
     */
    public void printRational()
    {
        System.out.println(this + ". numerator = " + getNumerator() + " denominator = " + getDenominator());
    }


    /**
     * Changes the sign of the fraction by multiplying its numerator by -1.
     */
    public void negate()
    {
        setNumerator(getNumerator() * -1);  // multiplication by -1 to change sign
    }


    /**
     * Flips a fraction. Swaps the numerator and the denominator
     */
    public void invert()
    {
        int temp = getDenominator();   // hold temporary ref to the denominator

        setDenominator(getNumerator()); // replace the denominator
        setNumerator(temp);             // replace the numerator with the temp value
    }


    /**
     * Returns the floating point representation of the fraction.
     * @return the fraction as a number (decimal)
     */
    public double toDouble()
    {
        double numerator = (double)getNumerator();      // make appropriate case in order to avoid losing precisiong
        double denominator = (double)getDenominator();  // must cast to double as integer division is not ideal here

        return numerator / denominator;
    }


    /**
     * Reduces a rational number to its simplest terms. This is done by dividing both the numerator and denominator
     * by their Greatest Common Factor
     */
    public void reduce()
    {
        int GCD = getGCD();             // calculate the GCD

        setNumerator(getNumerator() / GCD);         // divide both parts by GCD
        setDenominator(getDenominator() / GCD);
    }


    /**
     * This method finds the GCD of a rational number. This is accomplished by leveraging the built in ]
     * behavior of the java.math.BigInteger. Instead of using a series of loops and if statements to
     * calculate GCD, I used a concise standard library method to accomplish the same task in fewer lines of code.
     *
     * @return The GCD of this rational number's numerator and denominator
     */
    private int getGCD()
    {
        BigInteger numerator = new BigInteger(Integer.toString(getNumerator()));     // create BigIntegers out of the numerator and
        BigInteger denominator = new BigInteger(Integer.toString(getDenominator())); // denominator

        return numerator.gcd(denominator).intValue(); // unbox back into int
    }


    /**
     * This is a class method that adds rational numbers. This is accomplished by creating a new rational number
     * and then assigning it to the result of the addition. After this, the number is reduced and returned.
     *
     * @param other A rational number to add to the current one
     * @return The reduced sum of a and other
     */
    public RationalNumber add(RationalNumber other)
    {
        RationalNumber ret = new RationalNumber();

        int numerator = (getNumerator() * other.getDenominator()) + (getDenominator() * other.getNumerator());   // execute cross multiplication of numerators
        int denominator = (getDenominator() * other.getDenominator());        // execute multiplication of denominators

        ret.setNumerator(numerator);            // populate the new rational number
        ret.setDenominator(denominator);

        ret.reduce();                           // reduce it

        return ret;                             // return it

    }
}

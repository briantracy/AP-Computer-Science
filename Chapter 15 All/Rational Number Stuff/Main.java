/**
 * Main Entry point for the application. Just a series of tests to prove the concepts of the RationalNumber class.
 */
public class Main {

    public static void main(String[] args) {

        RationalNumber rationalNumber = new RationalNumber(10, 12);
        rationalNumber.printRational();

        rationalNumber.reduce();
        rationalNumber.printRational();

        RationalNumber other = new RationalNumber(8, 12);

        RationalNumber added = rationalNumber.add(other);

        added.printRational();


        System.out.println("Done with adding / reducing. Now negating and inverting");

        RationalNumber normal = new RationalNumber(3, 4);
        normal.printRational();

        normal.invert();
        normal.printRational();

        normal.negate();
        normal.printRational();

        System.out.println("Done with negating / inverting. Now getting decimal representation");

        RationalNumber thirtyThreeNinetyNinths = new RationalNumber(33, 99);

        thirtyThreeNinetyNinths.printRational();
        System.out.println(thirtyThreeNinetyNinths.toDouble());

        thirtyThreeNinetyNinths.reduce();

        thirtyThreeNinetyNinths.printRational();
        System.out.println(thirtyThreeNinetyNinths.toDouble());




    }


}

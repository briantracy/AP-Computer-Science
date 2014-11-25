import java.util.*;


/**
 * A handful of test cases. Warning: This may look like lisp (not really) at times, but it is (most certainly) not lisp.
 */
public class Main
{
    /**
     * For test purposes to show that even custom classes can be used in the MapCar function
     * @return A piece of twine
     */
    public String toString()
    {
        return "Here I am in toString() of a Main object";
    }

    public static void main(String[] args)
    {

        /** Test the toString method that all objects have */
        toStringTest();

        /** Test the hashCode method that all objects have */
        testHashCode();

        /** Test the equalsMethod that all objects have, this time using some parameters */
        testEquals();

    }

    /**
     * <summary>
     *     This method mapcars a list using the toString method. This method is found on all types. The resulting list should
     *     simply be a textual representation of all the objects in the first list.
     * </summary>
     */
    private static void toStringTest()
    {
        System.out.println("===[Testing the toString method]===");

        String      toString            = "toString";
        Class[]     parameterClasses    = null;
        Object[]    parameters          = null;
        Object[]    beforeList          = new Object[] {

                "Hello I'm a String",
                "Hello I'm another String",
                new ArrayList<Object>(),
                new Integer(123),
                new Main()

        };

        Object[] afterList = MapCar.exec(toString, parameterClasses, parameters, beforeList);

        System.out.println("Our Input List is the following.\n" + Arrays.toString(beforeList));
        System.out.println("Our Parameters are the following.\n" + Arrays.toString(parameters));
        System.out.println("The resulting list of these method calls is the following.\n" + Arrays.toString(afterList));
        System.out.println("\n\n\n");
    }


    /**
     * <summary>
     *     This method test the hashCode method that is found on all types. The hashcode method is supposed to turn an
     *     instance into a 32bit integer representation for storing. It takes no arguments.
     * </summary>
     */
    private static void testHashCode()
    {
        System.out.println("===[Testing the hashCode method]===");

        String      hashCode            = "hashCode";
        Class[]     parameterClasses    = null;
        Object[]    parameters          = null;
        Object[]    beforeList          = new Object[] {

                "Hello I'm a String",
                "Hello I'm another String",
                new ArrayList<Object>(),
                new Integer(123),
                new Main()

        };

        Object[] afterList = MapCar.exec(hashCode, parameterClasses, parameters, beforeList);


        System.out.println("Our Input List is the following.\n" + Arrays.toString(beforeList));
        System.out.println("Our Parameters are the following.\n" + Arrays.toString(parameters));
        System.out.println("The resulting list of these method calls is the following.\n" + Arrays.toString(afterList));
        System.out.println("\n\n\n");
    }

    /**
     * <summary>
     *     This test case checks to see if any of the first list are equal to `123`. This is done through the use of the .equals
     *     method that all objects implement. Only the `new Integer(123)` object should be equal.
     * </summary>
     */
    private static void testEquals()
    {
        System.out.println("===[Testing the equals method]===");

        String      equals            = "equals";
        Class[]     parameterClasses    = new Class[] {

                Object.class

        };
        Object[]    parameters          = new Object[] {

                new Integer(123)

        };
        Object[]    beforeList          = new Object[] {

                "Hello I'm a String",
                "Hello I'm another String",
                new ArrayList<Object>(),
                new Integer(123),
                new Main()

        };

        Object[] afterList = MapCar.exec(equals, parameterClasses, parameters, beforeList);

        System.out.println("Our Input List is the following.\n" + Arrays.toString(beforeList));
        System.out.println("Our Parameters are the following.\n" + Arrays.toString(parameters));
        System.out.println("The resulting list of these method calls is the following.\n" + Arrays.toString(afterList));
        System.out.println("\n\n\n");
    }
}

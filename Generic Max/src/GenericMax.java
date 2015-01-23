import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *  Find the maximum value of ANY Collection or Array of ANY type
 */
public class GenericMax
{

    /**
     *  Demonstrate the max method on a few arrays and a few lists.
     *
     */
    public static void main(String[] args)
    {
        Integer[] ints      = $(10, 20, 30, 10, 20, 50, 70, 80, 30);
        String [] strs      = $("Hello", "Goodbye", "I'm Here", "Today");
        Boolean[] bools     = $(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);

        Collection<Integer> intList   = Arrays.asList(ints);
        Collection<String>  strList   = Arrays.asList(strs);
        Collection<Boolean> boolList  = Arrays.asList(bools);

        runTestCase("Array of Integers", ints);
        runTestCase("Array of Strings", strs);
        runTestCase("Array of Booleans", bools);

        runTestCase("Collection of Integers", intList);
        runTestCase("Collection of Strings", strList);
        runTestCase("Collection of Booleans", boolList);
    }

    /**
     *  <summary>
     *      This method is a very cool way to make an array. It uses the fact that a varargs can decay into arrays
     *      when necessary. This method transforms the following code:
     *
     *          String[] strings = new String[] { "Hello", "World" };
     *          Integer[] ints = new Integer[] { 1 , 2 , 3 };
     *
     *      Into the much more concise:
     *          String[] strings = $("Hello", "World");
     *          Integer[] ints = $(1,2,3);
     *
     *      It also works for any type since it is a generic method. So to make arrays of different types, we can use
     *      the same method! See the main method for examples.
     *  </summary>
     *
     *      I guess you could say this method is pure cash.
     */
    private static <$> $ $($ ... $)[] { return $; }

    /**
     *  <summary>
     *      This method is one of two methods named `max`. It takes an array of ANY type and returns
     *      the maximum value within it. The elements in the array must implement the Comparable interface.
     *
     *      This method exists to find the max of arrays. To find the max of a collection, see the other max.
     *      This method is also just a helper method for the second max, converting its argument to a Collection
     *      so it is compatible with the other max.
     *  </summary>
     */
    private static <T extends Comparable<T>> T max(T[] ts)
    {
        return max(Arrays.asList(ts));
    }

    /**
     *  <summary>
     *      This method is the second of two methods named `max`. It takes any collection and returns the maximum
     *      element with it. This method is hugely versatile because it is as general as it gets. This is due to
     *      two factors.
     *          1. It takes a Collection object. Collection is such a massively general term in Java that this method
     *              will handle almost anything thrown at it. To get an idea of how big Collection is, every Set, List,
     *              Queue, Dequeue is a Collection.
     *          2. This is a generic method so any type of collection is allowed, ie: A set of Strings or a Set of
     *          ArrayLists. As long as the elements are Comparable, this method will work.
     *
     *      This method performs in O(n) time. By looking at the Collections.max() source code, one can see that it is
     *      a simple traversal of the collections iterator, each iteration comparing to some max value.
     *
     *      This method would have been easy to implement so I chose to not reinvent the wheel and simply delegate
     *      to it.
     *
     *
     *  </summary>
     *
     */
    private static <T extends Comparable<T>> T max(Collection<T> collection)
    {
        return Collections.max(collection);
    }

    /**
     *      The next two methods are here to support the test cases in the main method. They are basically the same
     *      except for the fact that the first one takes a Collection and the second takes an Array
     */
    private static <T extends Comparable<T>> void runTestCase(String name, Collection<T> coll)
    {
        System.out.format("Returning the max of [%-23s]: ", name);
        System.out.println(max(coll));
    }
    private static <T extends Comparable<T>> void runTestCase(String name, T[] array)
    {
        System.out.format("Returning the max of [%-23s]: ", name);
        System.out.println(max(array));
    }
}

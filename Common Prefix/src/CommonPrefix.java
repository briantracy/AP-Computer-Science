import java.util.Arrays;

public class CommonPrefix
{

    public static void main(String[] args)
    {
        String[][] tests = $(
                $("Hello" , "Helium" , "Help"                                    ),
                $("Barry" , "Barn"   , "Barnicle"                                ),
                $("Banter", "Banner" , "Bane"    , "Band"                        ),
                $("Barium", "Bard"   , "Barn"    , "Barf"     , "Bart"           ),
                $("Mathew", "Mathias", "Matheo"  , "Mathilial", "Mathilde"       ),
                $("There" , "Will"   , "Not"     , "Be"       , "A Common Prefix")
        );

        for (String[] test : tests) {
            System.out.println("The Greatest Common Prefix of " + Arrays.toString(test) + " is " + commonPrefix(test));
        }
    }

    /**
     *  <summary>
     *      This method will find the longest prefix that a group of strings shares. The cool thing about this method
     *      is that it goes beyond simply finding the greatest prefix of TWO strings.
     *
     *      Up to 64,000 (http://stackoverflow.com/questions/18164255/what-is-the-maximum-of-number-of-arguments-for-varargs-in-java)
     *      strings can be compared by this method and their common prefix will be returned.
     *  </summary>
     *
     *  <precondition>
     *      Strings must contain at least one string.
     *  </precondition>
     *
     * @param strings The strings to find the biggest prefix of
     * @return The biggest prefix they all share
     */
    private static String commonPrefix(String ... strings)
    {
        /**
         *  Find the shortest string given. This is the maximum prefix they all share.
         */
        String shortest = shortestString(strings);

        /**
         *  For each character in the shortest string (max prefix) see if the other strings have this prefix.
         *  If not, return what we have so far.
         *  If so, repeat until the end of the shortest string which is the theoretical max prefix.
         */
        for (int i = 0; i < shortest.length(); i++) {
            for (String string : strings) {
                if (!string.startsWith(shortest.substring(0, i + 1))) {
                    return shortest.substring(0, i);
                }
            }
        }

        return shortest;
    }


    /**
     *  <summary>
     *      This method will return the shortest string of the given list.
     *      It is used by the above method to determine the biggest possible common prefix of a given list of strings.
     *  </summary>
     *
     *  <precondition>
     *      Strings must contain at least one string
     *  </precondition>
     * @param strings The var-args array of strings to be checked
     * @return The shortest string in the given array.
     */
    private static String shortestString(String ... strings)
    {
        String smallest = strings[0];
        for (String x : strings) {
            smallest = smallest.length() > x.length() ? x : smallest;
        }
        return smallest;
    }


    private static <$> $ $($ ... $)[] { return $; }
}

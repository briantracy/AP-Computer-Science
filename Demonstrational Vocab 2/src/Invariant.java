/**
 * This class demonstrates the creation of an invariant.
 */
public class Invariant
{

    public void demonstrate()
    {
        Main.header("Demonstrating An Invariant");
        /*      In the next loop, two variables are being updated.      */
        /**
         *
         *      i: Range = [0,10)
         *      j: Range = [10, 0)
         *
         *      i is incremented each iteration while j is decremented.
         *
         *      It is an invariant that at any point in this loop, i + j is always equal to ten because
         *      of the way the loop is structured.
         *
         *
         */

        for (int i = 0, j = 10; i < 10 && j > 0; i++, j--)
        {
            int z = i + j;      /// It is an invariant that z is always equal to 10

            if (z != 10) throw new IllegalStateException("Invariant is false <not possible>");

            System.out.println(z);
        }
    }
}


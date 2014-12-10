import java.util.ArrayList;
import java.util.Collections;


/**
  Randp - Exhaustive random number generator. This class creates a source of random numbers from [1,x] where `x` is the
  value passed to the constructor. Each time this classes  `nextInt` method is called, a new number from that range is
  returned. There are no duplicates and when no more unique numbers that have not been used in the given range, 0 is returned.


  Instead of using a random number generator and checking to see if the number it returns has already been used, I
  chose a different method. PRNGs in this situation due to the fact that no duplicates can be emitted.

  In order to simulate a random group of numbers in a range, I stored all elements in the range and simply shuffled them.
  Now each time one is removed from the set, it appears to be random. This solution is fast and reliable.

 */
public class Randp
{
    private ArrayList<Integer> numbers; // Our range of numbers. Will exist in a shuffled state to appear random

    /**
     * Constructor for creating random number generator in range [1, `upperBoundInclusive`].
     * @param upperBoundInclusive The highest number that will be generated from this instance.
     */
    public Randp(int upperBoundInclusive)
    {
        numbers = new ArrayList<Integer>(upperBoundInclusive);
        seed(upperBoundInclusive);
    }


    /**
     * This method is the most time consuming method in the class. It performs in O(n) as each number of the range must
     * be created and added to the array. The array is then shuffled.
     *
     * This O(n) time is a small sacrifice to pay as this method is only called once as apposed to a different implementation
     * where each time a number is generated, the entire list of previous numbers has to be checked.
     *
     * @param bound Upper bound of range
     */
    private void seed(int bound)
    {
        for (int i = 1; i <= bound; i++)
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    /**
     *  This method is the most important method of the Randp class. It is responsible for returning the next unique random
     *  number in the given range. If a unique number does not exist, 0 is returned.
     *
     *  This method is extremely fast as isEmpty() and remove() are O(1) operations of ArrayList.
     *
     *  If no more unique numbers exist, return 0. If there are unique numbers left, return the first one possible and remove it
     *  so it cannot appear again.
     *
     * @return The next random number if a unique one is present, else 0
     */
    public int nextInt()
    {
        return numbers.isEmpty() ? 0 : numbers.remove(0);
    }


    public static void main(String[] args) {

        Randp randp = new Randp(6);
        int x;

        while ((x = randp.nextInt()) != 0)
        {
            System.out.println("Random Number is " + x);
        }

        System.out.println("No More Random Numbers");
    }


}

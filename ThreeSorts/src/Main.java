/**
 *  ThreeSorts
 *
 *  This assignment was to recreate three sorting algorithms. The sorts to recreate were
 *      1) The Bubble Sort
 *      2) The Insertion Sort
 *      3) The Selection Sort
 *
 *  All of these algorithms could be implemented without the use of temporary arrays as they all acted upon the array
 *  by either splitting it into a sorted and unsorted section or sorting one element at a time.
 *
 *
 *  As an extra bonus, I decided to measure the time each sorting algorithm took in nanoseconds. I then compared these three
 *  times to the time produced by the the native Arrays.sort(int[] n) method. All three of my implementations were horribly
 *  outperformed by the native function.
 *
 *  I originally decided to time the functions and compare them to the native function in an effort to determine which method
 *  the native sort was using. My results lead me to believe that none of the three methods I implemented is being used in the
 *  native sort. The stark performance difference proves this.
 *
 *
 *  I followed up my research and found out that the sort actually being used is the Quicksort algorithm. Its time complexity is
 *  O(n log n) coinciding with the quote from Mr. Kuszmaul
 *
 *          "All good algorithms perform in O(n log n)"
 *
 *
 */

import java.util.Arrays;

public class Main
{
    static final int ARRAY_SIZE = 10000;   // How big each array to be sorted is
    static final int MAX_VALUE  = 1000;    // Maximum value in each array


    static long nanos;

    public static void main(String[] args)
    {
        /** Populate the Lists */
        int[] bubble = randomArray();
        int[] insert = randomArray();
        int[] select = randomArray();
        int[] system = randomArray();


        /** BubbleSort test case */
        System.out.println("bubble before sort = " + Arrays.toString(bubble));
        start();
        bubbleSort(bubble);
        stop();
        System.out.println("bubble after  sort = " + Arrays.toString(bubble) + "\n");

        /** Insertion Sort test case */
        System.out.println("insert before sort = " + Arrays.toString(insert));
        start();
        insertionSort(insert);
        stop();
        System.out.println("insert after  sort = " + Arrays.toString(insert) + "\n");

        /** Selection Sort test case */
        System.out.println("select before sort = " + Arrays.toString(select));
        start();
        selectionSort(select);
         stop();
        System.out.println("select after  sort = " + Arrays.toString(select) + "\n");


        /** System sorting algorithm test case */
        System.out.println("system before sort = " + Arrays.toString(system));
        start();
        Arrays.sort(system);
        stop();
        System.out.println("insert after  sort = " + Arrays.toString(system) + "\n");




    }


    /** Bubble Sort
     *  <summary>
     *      This algorithm sorts an array using the Bubble Sort method. The outer loop iterates from the top of the
     *      array to the bottom, each index representing the "frozen" element of the array. The inner loop travels from the
     *      bottom of the array up to the current top, swapping each element with the one above it if they are out of order.
     *      By doing this, the largest element "bubbles" to the top, giving this algorithm its name.
     *  </summary>
     * @param in The array to be sorted
     */
    private static void bubbleSort(int[] in)
    {
        for (int top = in.length - 1; top > 0; top--)
        {
            for (int idx = 0; idx < top; idx++)
            {
                int i = in[idx];
                int j = in[idx + 1];

                if (i > j) {
                    in[idx] = j;
                    in[idx + 1] = i;
                }
            }
        }
    }


    /** Insertion Sort
     * <summary>
     *     This algorithm sorts an array using the Insertion Sort method. The outer loop starts at 1 and goes to the top
     *     of the array. This index represents the top of the sorted section of the array. From this point, the second loop
     *     descends, all the way to the bottom of the array. The algorithm now places the first element from the unsorted half of the
     *     array into its correct location in the sorted half.
     * </summary>
     *
     * @param in The array to be sorted
     */
    private static void insertionSort(int[] in)
    {
        for (int top = 1; top < in.length; top++)
        {
            int current = in[top];

            for (int descending = top - 1; descending >= 0 && in[descending] > current; descending--)
            {
                in[descending + 1] = in[descending];
                in[descending] = current;
            }
        }
    }


    /** Selection Sort
     * <summary>
     *     This algorithm sorts an array using the Selection Sort method. The outer loop iterates from the bottom to the top
     *     of the array. The ith element in this loop represents how much of the array is currently sorted. The array is sorted by
     *     finding the smallest element and putting it at the head of the sorted section. The next iteration picks up at the next element and
     *     repeats the same process, each iteration growing the size of the sorted array and decreasing the size of the unsorted section.
     * </summary>
     *
     * @param in The array to be sorted
     */
    private static void selectionSort(int[] in)
    {
        int indexOfMin = 0;
        int min = Integer.MAX_VALUE;

        for (int idx = 0; idx < in.length; idx++)
        {
            min = in[idx];
            indexOfMin = idx;
            for (int unsortedIter = idx; unsortedIter < in.length; unsortedIter++)
            {
                int current = in[unsortedIter];
                if (current < min) {
                    min = current;
                    indexOfMin = unsortedIter;
                }
            }

            in[indexOfMin] = in[idx];
            in[idx] = min;
        }
    }


    /** Random Array
     * <summary>
     *     This method creates and returns a random array for testing. See the static fields of this class
     *     for more information about the random array.
     * </summary>
     *
     * @return A randomized array with numbers in the range [0, MAX_VALUE).
     */
    private static int[] randomArray()
    {
        int[] ret = new int[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            ret[i] = (int)(Math.random() * MAX_VALUE);
        }

        return ret;
    }


    /**
     * Timing Functions used in testing. Uses System.nanoTime() for increased precision greater than System.currentTimeMillis()
     */
    private static void start()
    {
        nanos = System.nanoTime();
    }

    private static void stop()
    {
        long elapsed = System.nanoTime() - nanos;
        System.out.println("Nanoseconds Elapsed " + elapsed + "ns.");
        nanos = 0;
    }


}

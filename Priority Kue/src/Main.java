
import java.util.*;


public class Main
{
    private static final CustomComparator<String> GLOBAL_COMPARATOR = new CustomComparator<String>();


    /**
     *  <summary>
     *      This class is used in the efficiency test case. It is a comparator that keeps track of
     *      how many comparisons it makes. It is used to judge the efficiency of any algorithms that use
     *      it.
     *  </summary>
     * @param <T> Generic Type
     */
    private static class CustomComparator<T extends Comparable<T>> implements Comparator<T> {
        int comparisons = 0;

        public int compare(T var1, T var2)
        {
            comparisons++;
            return var1.compareTo(var2);
        }

        public void reset() { comparisons = 0; }

    }

    public static void main(String[] args)
    {
 //       runAccuracyTest();
//        int iters = 100;
//
//        ArrayList<Long> times = new ArrayList<Long>();
//
//        for (; iters <= 100000; iters *= 10) {
//            times.add(runTest(iters));
//            System.out.println("=======");
//        }
//
//        System.out.println(times);
//        System.out.println("terminated");

        runAccuracyTest();
        runEfficiencyTest();

    }

    private static long runTest(int iters)
    {
        GLOBAL_COMPARATOR.reset();
        PriorityQueue<String> queue = new PriorityQueue<String>(GLOBAL_COMPARATOR);

        long start = System.currentTimeMillis();

        for (int i = 0; i < iters; i++) {
            queue.add(randomString());
        }

        int global = 0;

        for (int i = 0; i < 10; i++) {
            GLOBAL_COMPARATOR.reset();


            String rnd = randomString();
            queue.add(rnd);
            System.out.println("comparisons this add/removal " + GLOBAL_COMPARATOR.comparisons );
            global += GLOBAL_COMPARATOR.comparisons;

            queue.delete(rnd);

        }

        long elapsed = System.currentTimeMillis() - start;
        //System.out.println("Test case with " + iters + " iterations took " + elapsed + "ms" + " comparisons made " + GLOBAL_COMPARATOR.comparisons);


        return global;
    }

    private static String randomString()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * <summary>
     *     This is the test for accuracy of implementation. In this test, two behaviors of the fake queue
     *     are being put under the microscope. The first is the ability to correctly add elements in the right order.
     *     The second is to correctly retrieve elements in the right order. This is tested by putting the fake queue
     *     and the java.util.PriorityQueue head to head.
     *
     *     Both queues (real and fake) are filled with 10 random UUIDs. They are inserted with the `GLOBAL_COMPARATOR`
     *     object. Both queues are then printed. The same output will be seen on both lines. Printing is acheived
     *     by repeatedly polling the queue until it is empty.
     *
     * </summary>
     */
    private static void runAccuracyTest()
    {
        createTitle("Running Accuracy Test");
        java.util.PriorityQueue <String> real = new java.util.PriorityQueue<String>(1, GLOBAL_COMPARATOR);
        PriorityQueue           <String> fake = new PriorityQueue<String>(GLOBAL_COMPARATOR);

        for (int i = 0; i < 10; i++) {
            String randomString = randomString();
            real.add(randomString);
            fake.add(randomString);
        }

        System.out.print("Real Priority Queue: ");
        while (!real.isEmpty()) {
            System.out.print(real.poll() + ", ");
        }

        System.out.print("\nFake Priority Queue: ");
        while (!fake.isEmpty()) {
            System.out.print(fake.poll() + ", ");
        }
        System.out.println();
        createTitle("Ending  Accuracy Test");
    }

    /**
     * <summary>
     *     This test is for efficiency of implementation. It will ensure that adding to the queue is done in
     *     logarithmic time.
     *
     *     The output of this test is a series of (not very clear) numbers that correspond to number of comparisons
     *     made at differing queue sizes by the insertion algorithm. These numbers can be better visualized by the
     *     attached graph.
     * </summary>
     */
    private static void runEfficiencyTest()
    {
        createTitle("Running Efficiency Test");

        for (int i = 100; i <= 100000; i *= 10) {
            int comparisons = performEfficiencyMeasurementAtQueueSize(i);

            System.out.printf("A tree of size %d makes %d comparisons to insert. (%d, %d)\n", i, comparisons, i, comparisons);
        }

        createTitle("Ending  Efficiency Test");
    }

    /**
     *  <summary>
     *      This helper method runs a single test case on a queue of a certain size. It builds up the queue to the given
     *      size then measures how many comparisons are made int the process of inserting another element. This process is
     *      done 10 times to decrease the margin of error. The compound number of comparisons over all 10 insertions is then
     *      the final result of the function.
     *  </summary>
     * @param size see @return
     * @return The number of comparisons that are made when inserting into a queue of size `size` over 10 test cases.
     */
    private static int performEfficiencyMeasurementAtQueueSize(int size)
    {
        PriorityQueue<String> queue = new PriorityQueue<String>(GLOBAL_COMPARATOR);

        /**
         * Build a queue up to `size` size.
         */
        for (int i = 0; i < size; i++) {
            queue.add(randomString());
        }

        int compoundComparisons = 0;

        for (int i = 0; i < 10; i++) {
            GLOBAL_COMPARATOR.reset();

            String rnd = randomString();

            queue.add(rnd);

            compoundComparisons += GLOBAL_COMPARATOR.comparisons;

            queue.delete(rnd);
        }
        return compoundComparisons;
    }


    /**
     * <summary>
     *     Purely aesthetic. This function puts a nice separator around a title to make it stand out.
     * </summary>
     * @param str A title
     */
    private static void createTitle(String str)
    {
        int length = str.length() + 8;
        for (int i = 0; i < length; i++) { System.out.print("="); }
        System.out.println("\n\t" + str);
        for (int i = 0; i < length; i++) { System.out.print("="); }
        System.out.println();
    }
}
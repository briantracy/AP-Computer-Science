
public class CollatzNonMemoized
{
    private int max;

    public CollatzNonMemoized(int max)
    {
        this.max = max;
    }


    public void run()
    {
        int longestNum = 0;
        int longestIters = 0;

        for (int i = 1; i < max; i++)
        {
            int iters = 0;
            int n = i;

            while (n > 1) {
                iters++;
                if (n % 2 == 0) {
                    n /= 2;
                }
                else {
                    n *= 3;
                    n += 1;
                }
            }
            // n is now 1

            if (iters > longestIters) {
                longestIters = iters;
                longestNum = i;
            }
        }

        System.out.println("The Longest Number is " + longestNum + " with iters = " + longestIters);
    }
}

import java.util.HashMap;

public class CollatzMemoized
{
    private int max;

    private HashMap<Integer, Integer> table;

    public CollatzMemoized(int max)
    {
        this.max = max;
        table = new HashMap<Integer, Integer>(max);
    }




    public void run()
    {
        int longestNum = 0;
        int longestIters = 0;

        for (int i = 0; i < max; i++)
        {
            int iters = 0;
            int n = i;

            while (n > 1) {
                iters++;
                if (table.containsKey(n)) {
                    iters += table.get(n);
                    break;
                }

                if (n % 2 == 0) {
                    n /= 2;
                }
                else {
                    n *= 3;
                    n += 1;
                }
            }
            table.put(i, iters);

            if (iters > longestIters) {
                longestIters = iters;
                longestNum = i;
            }
        }

        System.out.println("The Longest Number is " + longestNum + " with iters = " + longestIters);

    }
}

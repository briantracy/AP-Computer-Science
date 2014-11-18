public class Main
{

    public static void main(String[] args)
    {

        System.out.println(f_recursive(5));
    }


    private static int f_recursive(int n)
    {
        if (n < 3) return n;

        return f_recursive(n - 1) + (2 * f_recursive(n - 2)) + (3 * f_recursive(n - 3));
    }

    private static int f_iterative(int n)
    {
        int accumulator = 1;



        return accumulator;

    }










}

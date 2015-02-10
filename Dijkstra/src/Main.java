import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        int[][] adj = new int[][] {

                { 1, 2, 3, 4},
                { 2, 3, 4, 5},
                { 3, 4, 6, 7},
                { 4, 5, 6, 7}
        };

        Graph graph = new Graph(adj);

        System.out.println("done");

    }
}

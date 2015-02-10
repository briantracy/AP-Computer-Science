import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        int[][] example1 = new int[][] {
                { 0, 3, 0, 15 },
                { 0, 0, 4, 10 },
                { 7, 0, 0, 2  },
                { 0, 0, 0, 0  }
        };

        int[][] example2 = new int[][] {
                { 0, 7, 10, 5 },
                { 0, 0, 8, 11 },
                { 0, 0, 0, 9 },
                { 0, 11, 0, 0 },
        };


        runTestCase(example1, "Example 1");
        runTestCase(example2, "Example 2");
    }

    private static void runTestCase(int[][] example, String name)
    {
        Graph graph = new Graph(example);
        graph.setStartingNode(0);
        graph.runDijkstra();

        System.out.println("Testing Example " + name);
        graph.printOutput();
    }
}

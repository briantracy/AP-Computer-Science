import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        int[][] example1 = new int[][] {

                { 0, 3, 0, 15},
                { 0, 0, 4, 10},
                { 7, 0, 0, 2},
                { 0, 0, 0, 0}
        };
        runTestCase(example1, "First Graph");

        int[][] example2 = new int[][] {

        };


        Visualizer v = new Visualizer();


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

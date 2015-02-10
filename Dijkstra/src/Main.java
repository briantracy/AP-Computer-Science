import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        int[][] adj = new int[][] {

                { 0, 3, 0, 15},
                { 0, 0, 4, 10},
                { 7, 0, 0, 2},
                { 0, 0, 0, 0}
        };

        int[][] presentation = new int[][] {

                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        Graph graph = new Graph(adj);

        graph.setStartingNode(0);

        graph.runDijkstra();

        graph.printOutput();

        System.out.println("done");

    }
}

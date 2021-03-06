import java.util.ArrayList;

/**
 *      See Accompanying jpeg file for a picture of the graph that we are testing.
 */
public class Main
{
    /**
     *  The methods on the Graph object are designed to be as easily readable as possible. Note how the methods called
     *  on a graph instance have descriptive names that are meant to be "self documenting".
     */
    public static void main(String[] args)
    {
        int[][] example = new int[][] {
                { 0, 3, 0, 15 },
                { 0, 0, 4, 10 },            /// Curious as to what this looks like? Take a gander at the jpeg.
                { 0, 0, 0, 2  },
                { 0, 0, 0, 0  }
        };

        Graph graph = new Graph(example);
        graph.setStartingNode(0);
        graph.runDijkstra();

        /// Sample output is provided -- see README.txt
        graph.printOutput();

        x("Hello");
        x(new ArrayList());

    }


    public static void x(Object x) {
        System.out.println("Object");
    }


    public static void x(String x) {
        System.out.println("String");;}
}


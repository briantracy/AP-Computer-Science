import java.util.ArrayList;
import java.util.Arrays;

public class Graph
{

    int[][] adjacencyMatrix;

    Node[] nodes;


    public Graph(int[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;
        System.out.println(Arrays.deepToString(this.adjacencyMatrix));

        nodes = new Node[this.adjacencyMatrix.length];


        createNodes();
        print();
    }


    private int weight(Node a, Node b)
    {
        return adjacencyMatrix[a.id][b.id];
    }

    private void createNodes()
    {
        for (int i = 0; i < nodes.length; i++)
        {
            Node node = new Node(i);
            nodes[i] = node;
        }

        for (int row = 0; row < nodes.length; row++)
        {
            for (int col = 0; col < nodes.length; col++)
            {
                if (adjacencyMatrix[row][col] != 0) {
                    nodes[row].addNeighbor(nodes[col]);
                }
            }
        }
    }

    private void print()
    {
        for (Node nd : nodes) {
            System.out.println("Node " + nd.id + " : " + nd.neighbors);
        }
    }

}

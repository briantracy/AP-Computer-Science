import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Graph
{

    int[][] adjacencyMatrix;

    Node[] nodes;

    int start;


    public Graph(int[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;

        nodes = new Node[this.adjacencyMatrix.length];

        createNodes();
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
            nodes[i].lastNode = nodes[i];
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

    public void setStartingNode(int num)
    {
        this.start = num;
    }

    public void runDijkstra()
    {
        nodes[start].distance = 0;

        PriorityQueue<Node> queue = new PriorityQueue<Node>(Arrays.asList(nodes));

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (Node neighbor : current.neighbors)
            {
                int weight = weight(current, neighbor);

                int newDistance = current.distance + weight;

                if (newDistance < neighbor.distance) {
                    neighbor.distance = newDistance;
                    neighbor.lastNode = current;
                }
            }
        }

    }

    public void printOutput()
    {
        for (Node nd : nodes) {
            System.out.println(String.format("Node %s | Distance %-2d | Last Visited %s",
                                            nd.alphaID(),
                                                        nd.distance,
                                                                        nd.lastNode.alphaID()));
        }
    }
}

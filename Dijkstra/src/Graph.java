import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 *      This class represents a Graph, the playing field for Dijkstra's Algorithm.
 *
 *      This class is the bridge between the cold world of Adjacency Matrices and the nice and cozy world
 *      of Object Oriented Graph Theory.
 *
 *      A graph is composed of Nodes and Edges. This graph stores its Nodes and calculates its edges on the fly. This
 *      behaviour will be explained farther down.
 *
 *      The first responsibility of this class is to store the Adjacency Matrix that the algorithm requires. This is
 *      done in the form of a standard two dimensional array.
 *      Non-Zero elements in the matrix represent weighted edges between the nodes at [row][column].
 *
 *      The second responsibility of this class is to store the nodes present in the graph. This is done with a
 *      single dimensional array of nodes. It is not an ArrayList because we know EXACTLY how many nodes are present
 *      in a graph.
 *
 *      The last responsibility of this class is to keep track of which node is the starting node. The only requirement
 *      for Dijkstra's Algorithm is that we know which node to start with.
 *
 *      All three of these responsibilities are reflected in the following fields.
 *
 */
public class Graph
{
    /**
     *  ADJACENCY MATRIX: The adjacency matrix representing this graph. It is used only in two situations.
     *      1. Creating the Nodes.
     *      2. Calculating Distances between Nodes.
     */
    private final int[][] adjacencyMatrix;

    /**
     *  NODES: Each graph is made up of a definite number of nodes and this is how we keep track of them. Nodes
     *  are not really in any order, but using an array is the easiest way to go.
     *
     *  This array is also really only used in two places.
     *      1. Populating the PriorityQueue
     *      2. Final Printing of Result
     */
    private Node[] nodes;

    /**
     *   Mark which node is the starting node. Corresponds to an index in the previously mentioned Node[].
     */
    private int start;


    /**
     *  CONSTRUCTOR
     *  <summary>
     *      This is the sole constructor of the Graph class. It takes a two dimensional array that represents
     *      the adjacency matrix that we shall construct this graph around.
     *
     *      Three important things happen in this constructor.
     *          1. Cache the adjacency matrix for later use.
     *          2. Allocate our Node[] so that we may populate it later.
     *          3. Give control to the createNodes() method.
     *
     *
     *
     *  </summary>
     *
     * @param adjacencyMatrix The adjacency matrix from which we are building this graph.
     */
    public Graph(int[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;

        nodes = new Node[this.adjacencyMatrix.length];

        createNodes();
    }


    /**
     *  <summary>
     *      This method is the pre-requisite for running Dijkstra's Algorithm.
     *
     *      Before we can do anything, we must initialize the nodes that our graph contains. The initialization process
     *      takes three steps.
     *          1. Create the node and set its ID to the current value of the loop iterator. This allows it to know its
     *          position relative to the other nodes.
     *          2. Put the node in the correct position in the Node[]
     *          3. Set the node's `lastNode` to itself so that we don't have to do it later. This also initializes its
     *          `lastNode` property so we can avoid an NPE's that might arise.
     *
     *      That was part one of this method, the creation phase. The second phase is to hook up the nodes to eachother
     *      according to the adjacency matrix.
     *
     *      We have to comprehend the adjacency matrix so we double-iterate over it. In the inner loop, we perform
     *      a critical calculation that reference the adjacency matrix. This is the first reason that we cached the
     *      adjacency matrix as a field of this class.
     *
     *      In the tight loop, we check if the value at the current position mat[x][y] is non-zero. This means that the nodes
     *      at [row] and [col] are connected. If they are non-zero, then we add them to each others `neighbors` array.
     *
     *      This process of "linking up" the nodes is critical and must be performed before we can run Dijkstra's Algorithm.
     *
     *      By the end of this method, we will have fully comprehended the Adjacency Matrix and we will have fully
     *      created our graph. Now it is time to actually run the algorithm.
     *  </summary>
     */
    private void createNodes()
    {
        for (int i = 0; i < nodes.length; i++)
        {
            /**
             *  Create the new node. Put it in our array. Make it its own last element.
             */
            Node node = new Node(i);
            nodes[i] = node;
            nodes[i].lastNode = nodes[i];
        }

        for (int row = 0; row < nodes.length; row++)
        {
            for (int col = 0; col < nodes.length; col++)
            {
                /**
                 *  Determine if the two nodes denoted by [row] and [col] are connected. If so, make them neighbors.
                 */
                if (adjacencyMatrix[row][col] != 0) {
                    nodes[row].addNeighbor(nodes[col]);
                }
            }
        }
    }

    /**
     *  <summary>
     *      This method is a simple setter for our starting position.  It reads nicely from the outside (see Main.java).
     *      The start value is important because without it, Dijkstra's Algorithm would spin endlessly as all of the
     *      node's tentative distance values would be Infinity.
     *  </summary>
     *
     */
    public void setStartingNode(int num)
    {
        this.start = num;
    }

    /**
     *  <summary>
     *      This method is the second reason we cache the adjacency matrix.
     *
     *      This method determines the weight of the edge that connects two nodes. This is accomplished by referring
     *      back to the adjacency matrix.
     *
     *      We must remember that the adjacency matrix does more than tell us if two nodes are connected. It also tells
     *      us how closely connected they are, the value at a given [row] and [col] is equal to the distance between the
     *      nodes at [row] and [col].
     *
     *      Recall that the `id` field of a node holds where it is in the graph. For example, 0 relates to the first
     *      node in the graph, the letter 'A' in some visualizations, and the first column and row in the adjacency matrix.
     *
     *      Indexing the adjacency matrix by a nodes `id` property allows us to gain acess to information about that node
     *      stored in the adjacency matrix.
     *
     *      <important>
     *              The value at the A.id-th row and A.id-th column is the distance between node A and node B as per the
     *              definition of an Adjacency Matrix.
     *      </important>
     *
     *  </summary>
     *
     *  @return The DISTANCE between nodes A and B according to the adjacency matrix.
     */
    private int weight(Node a, Node b)
    {
        return adjacencyMatrix[a.id][b.id];
    }

    /**
     *  
     */
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

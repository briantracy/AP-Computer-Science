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
     *
     *      (note: it would not actually spin endlessly, but the results would be garbage)
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
     *  Behold Dijkstra's Algorithm. Coming in at only 10 statements, it holds the simplicity of a recursive
     *  algorithm without the ensuing complexity.
     *
     *  <summary>
     *      Little setup is needed before the guts of Dijkstra's Algorithm can be executed. The first is to
     *      declare a starting node. This is crucial because without it, the program would not perform properly. Declaring
     *      the starting node is as easy as setting the start-th element in our node array to have a distance of 0. This
     *      immediately makes it the first element in the PriorityQueue because 0 < Integer.MAX_VALUE which is the default
     *      tentative distance.
     *              1. Declare a starting node.
     *
     *      The next thing we have to do is make the PriorityQueue that contains each node in the graph. This is an easy
     *      one-liner with the help of the Arrays.asList() method and the PriorityQueue constructor that takes a list. The
     *      PriorityQueue is needed because we always want to move to the next node that has the lowest tentative distance.
     *
     *      Remember that the comparator in the Node class is based on distance so the Queue will naturally be ordered
     *      by each node's distance.
     *              2. Construct a PriorityQueue of nodes
     *
     *      This next step is the first of the actual Dijkstra Code. We must loop until the Queue is empty. This
     *      represents the algorithm visiting all of the nodes in the graph.
     *
     *      <important>
     *          Note that the polling from the Queue is not as static as it may seem. As the body of the while loop
     *          is executed, distance of ALL of the nodes might change, affecting the outcome of the next poll() call.
     *
     *          While it may seem like a straight up polling out of the Queue, there is a lot more going on behind the
     *          scenes that affects the Queue.
     *      </important>
     *
     *      We must perform the body of Dijkstra's Algorithm untill the PriorityQueue is empty.
     *              3. Dynamically Iterate over the PriorityQueue, simulating visiting all nodes.
     *
     *      The next step is to get the current element sequence, the first element in the PriorityQueue. This represents
     *      the current node we are looking at. It is removed from the PriorityQueue (note the use of poll()).
     *              4. Remove the current node
     *
     *      Once we have the current node, we need to look at all of the nodes it is connected to and determine their
     *      total distances.
     *              5. Iterate through all of the neighbors of the current node.
     *
     *      For each neighbor, we calculate the distance between that neighbor and the start node. This is accomplished
     *      first by finding the weight between the current node and target neighbor.
     *              6. Determine the weight between the current node and a neighbor node.
     *
     *      Once we have the weight to the target node, add it to the distance of the current node to find the total
     *      path weight.
     *              7. Calculate total path weight to a certain neighbor node.
     *
     *      Just because we have calculated this total path weight does not mean that we should keep it. Next we have
     *      to compare it to the existing distance value of the node we are looking at. Assign the max of these two values
     *      to the nodes distance. We have now calculated the (for now) shortest distance.
     *              8. Determine whether or not a certain distance to a node is in fact the best distance we have found.
     *
     *      The next two steps simply update the state of the algorithm as it goes along, making sure that successive
     *      generations know exactly what is going on. First we need to actually assign the distance calculated to the
     *      distance of a certain node. This distance may not always be different that the distance discovered in previous
     *      iterations.
     *              9. Assign a tentative distance. This may be freshly calculated or an older distance that remains optimal.
     *
     *      The last step in the process is to update each node's `lastNode` property so we can trace a route back to the
     *      starting node. See getPathOfNode() method.
     *              10. Assign a lastNode to the current node. This is only a new node if the distance calculated was optimal.
     *
     *
     *      And that's it, only 10 statements and we have found the best path to every node in a graph given a starting point!
     *
     *      To recap, the ten labeled steps from above:
     *               1. Declare a starting node.
     *               2. Construct a PriorityQueue of nodes.
     *               3. Dynamically Iterate over the PriorityQueue, simulating visiting all nodes.
     *               4. Remove the current node.
     *               5. Iterate through all of the neighbors of the current node.
     *               6. Determine the weight between the current node and a neighbor node.
     *               7. Calculate total path weight to a certain neighbor node.
     *               8. Determine whether or not a certain distance to a node is in fact the best distance we have found.
     *               9. Assign a tentative distance. This may be freshly calculated or an older distance that remains optimal.
     *               10. Assign a lastNode to the current node. This is only a new node if the distance calculated was optimal.
     *  </summary>
     */
    public void runDijkstra()
    {
        nodes[start].distance = 0;                                  /// REQUIRED: A starting node.

        PriorityQueue<Node> queue = new PriorityQueue<Node>(Arrays.asList(nodes));      /// See Node.compareTo()

        while (!queue.isEmpty()) {                                  /// Visit every node

            Node current = queue.poll();                            /// Which node are we focusing on?

            for (Node neighbor : current.neighbors)                 /// Scan each of its connected nodes.
            {
                int weight = weight(current, neighbor);             /// Delegate to the adj mat, see Graph.weight()
                int newDistance =
                        (current.distance == Integer.MAX_VALUE ?    /// Calculate the total distance to this node.
                                0 :
                                current.distance
                        )
                                + weight;

                if (newDistance < neighbor.distance) {              /// Was this new path optimal?
                    neighbor.distance = newDistance;                /// Yes it was, make not of that
                    neighbor.lastNode = current;                    /// Make sure we can track back to the start.
                }
            }
        }
    }

    /**
     *  This method represents the final output after performing Dijkstra's Algorithm on a graph given a
     *  starting node
     *
     *  <summary>
     *      This method emits a table containing the final results of the algorithm. It is formatted as such:
     *          (let X = <some Node in graph>)
     *
     *          X's ID | Distance to X from start | Last Node before X in shortest path to X
     *  </summary>
     */
    public void printOutput()
    {
        for (Node nd : nodes) {
            System.out.print(String.format("Node %s | Distance %-2d | Last Visited %s | ",
                                            nd.alphaID(),
                                                         nd.distance,
                                                                        nd.lastNode.alphaID()));

            getPathOfNode(nd.id);
        }
    }

    /**
     *      ***BONUS METHOD***
     *
     *  This method will actually trace the fastest path computed by Dijkstra's Algorithm.
     *
     *  <summary>
     *      This method displays the fastest path from a given node back to the starting node.
     *      This is done by tracing back through each nodes `lastNode` property until we hit the
     *      first node. We can tell a node is the first when its `lastNode` property is THE SAME REFERENCE AS itself.
     *
     *      It may seem strange, but the break condition in this loop is whether two objects are equal
     *      through the use of the `==` operator. THIS IS INTENTIONAL as we are checking their references.
     *
     *      <important>
     *               To understand why the first nodes `lastNode` property is identical to the node itself, see the createNodes() method
     *      </important>
     *  </summary>
     *
     */
    public void getPathOfNode(int index)
    {
        /**
         *  Sanitize the input.
         */
        if (index >= nodes.length) { System.err.println("Invalid Node"); return; }

        System.out.print("The shortest path between [Node " + nodes[index].alphaID() +
                "] and [The Starting Node (" + nodes[start].alphaID() + ")] is [");

        Node dest = nodes[index];
        System.out.print(dest.alphaID());

        while (true) {
            if (dest == dest.lastNode) {
                /**
                 *  We have reached the beginning of the path where a node is its own last node. SEE createNodes().
                 */
                break;
            }

            /**
             *  Walk the path back to the starting node.
             */
            dest = dest.lastNode;

            System.out.print("-" + dest.alphaID());
        }
        System.out.println("].");
    }
}

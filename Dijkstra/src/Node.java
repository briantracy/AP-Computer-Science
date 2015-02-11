import java.util.ArrayList;

/**
 *  <summary>
 *      This class represents a single node in a graph. A graph is composed of Nodes and Edges, this class deals with
 *      the former.
 *
 *      Each node is represented by its Name (A, B, C, D, ...) or in the case of this class, a Number. For example,
 *      the letters "A-D" map to the numbers "0-3".
 *
 *      Each node is also contains an array of the nodes that it is connected to. This is known as the neighbors of this
 *      node.
 *
 *      NOTE: All fields are public by default. This is intentional.
 *
 *  </summary>
 */
public class Node implements Comparable<Node>
{
    /**
     *  ID: Which node this is in the greater scheme of the graph.
     */
    int id;

    /**
     *  NEIGHBORS: An ArrayList of the nodes that this node is in contact with.
     */
    ArrayList<Node> neighbors;

    /**
     *  The tentative distance of this node. It starts at Infinity to show that we do not know it. The tentative
     *  distance is how we rank nodes in a PriorityQueue
     */
    int distance = Integer.MAX_VALUE;    /// This represents the fact that we do not know its distance yet

    /**
     *  The last node that we went through to get to this node. Used in the final output of the algorithm.
     */
    Node lastNode;


    /**
     *  <summary>
     *      This method is the sole requirement of the Comparable interface. This interface allows us to put our Node
     *      class inside of a PriorityQueue with the desired behaviour.
     *
     *      Once inside a PriorityQueue, we need a system to rank a node. In Dijkstra's algorithm, we rank a node by its
     *      total distance from the starting node, so simply compare the node's `distance` property.
     *  </summary>
     * @param node The node we are comparing to.
     */
    @Override
    public int compareTo(Node node) {
        return  Integer.valueOf(distance).compareTo(node.distance);
    }

    /**
     *  CONSTRUCTOR
     *  <summary>
     *      This is the sole constructor of the Node class. It sets the ID of this node. When nodes are created, this
     *      is really the only thing we need to know.
     *
     *      The second half initializes the neighbors ArrayList so that we can simply append to it when the time comes.
     *  </summary>
     *
     */
    public Node(int id)
    {
        this.id = id;
        this.neighbors = new ArrayList<Node>();
    }


    /**
     *  <summary>
     *      This method is simply a utility method that makes adding to the neighbors array easier. Because adding
     *      a neighbor is a common operation, I decided to encapsulate this behaviour into an easy to use method.
     *  </summary>
     * @param node The node to be added as a neighbor of this node.
     */
    public void addNeighbor(Node node)
    {
        this.neighbors.add(node);
    }

    /**
     *  <summary>
     *      This method is purely for aesthetic purposes. It serves as a textualizer for a node and is how we can
     *      display a node to the user. This is the bridge between numeric ID's of nodes and the letters that are
     *      normally used to describe nodes on graphs.
     *
     *      This method leverages the fact that characters are  unsigned 16 bit integers and can be manipulated as such.
     *      By adding 'A' to this nodes ID, we derive the ASCII character that maps to its numerical id.
     *
     *      For example, 0 -> 'A', 1 -> 'B', 2 -> 'C'
     *
     *  </summary>
     *
     */
    public String alphaID()
    {
        return new Character((char)('A' + id)).toString();
    }
}

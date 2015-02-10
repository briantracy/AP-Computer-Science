import java.util.ArrayList;

public class Node implements Comparable<Node>
{

    int id;

    ArrayList<Node> neighbors;

    boolean isVisited = false;

    int distance = Integer.MAX_VALUE;    // not yet known

    Node lastNode;


    @Override
    public int compareTo(Node node) {
        return  Integer.valueOf(distance).compareTo(node.distance);
    }

    public Node(int id)
    {
        this.id = id;
        this.neighbors = new ArrayList<Node>();
    }


    public void addNeighbor(Node node)
    {
        this.neighbors.add(node);
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
}

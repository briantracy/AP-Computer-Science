import java.util.ArrayList;

public class Node
{

    int id;

    ArrayList<Node> neighbors;


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

/**
 *  A graph can be represented by an incidence matrix. It is a one dimensional array of integers that are arranged
 *  into a matrix.
 *
 *  Graphs are used in dijkstras algorithm to represent physical points and paths between them.
 */
public class GraphDemo
{

    /**
     * The following incidence matrix represents a graph with 4 nodes. This number can be attained by finding the length
     * of one of the sides of the "square" matrix. The length of a side of a square is the root of its area.
     */
    public void demonstrate()
    {
        int[] mat = new int[] {
                1,1,1,0,
                1,0,0,0,
                0,1,0,1,
                0,0,1,1
        };

        /**
         *  Calculate sidelength wich equals the # of nodes.
         */
        int dimension = (int)Math.sqrt(mat.length);

        System.out.println("\nNumber of nodes in this graph: " + dimension);
    }
}


public class BST<T extends Comparable<T>>
{

    /**
     *      ======
     *      Fields
     *      ======
     */
    private BSTNode<T> root;


    public BST()
    {

    }


    /**
     *      ================
     *      Accessor Methods
     *      ================
     */

    public BSTNode<T> getTree() { return root; }

    public boolean isLeaf()
    {
        return false;
    }




}


public class BSTNode<T extends Comparable<T>>
{

    private T datum;
    private BSTNode<T> left, right;




    public BSTNode(T datum)
    {
        this.datum = datum;
        left = right = null;
    }


    public boolean isLeaf()
    {
        return left == null && right == null;
    }


}


public class BSTNode<T extends Comparable<T>>
{

    private T datum;
    private BSTNode<T> left, right;


    public T getDatum() {
        return datum;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

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

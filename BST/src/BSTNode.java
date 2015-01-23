/**
 *  It is critical that <T extends Comparable> because in a BST, comparing nodes is critical. It will determine where
 *  a node goes in a tree so we need a mutually understood way to compare nodes. This is the job of the Comparable interface.
 * @param <T> Type of data
 */
public class BSTNode<T extends Comparable<T>>
{

    public T datum;
    public BSTNode<T> left, right;


    /**
     *  Important Decision:
     *      I decided to temporarily igonre of of the rather contrived examples of encapsulation. Instead of simply
     *      making private fields and their public getters, I chose to make the data and children public. There were two
     *      reasons I made this decision.
     *          1) The implementation of the getter and setter (for now) would have done nothing more than get and set the
     *          object. I understand that this might change, but due to number 2, this is unlikely,
     *          2) The nature of a nodes in a tree is very open. The nodes themselves are not that smart and don't really
     *          need to do much other than act as containers. For this reason, the boilerplate getter/setter code would have
     *          been overkill.
     *
     */

//    public T getDatum() {
//        return datum;
//    }
//
//    public BSTNode<T> getLeft() {
//        return left;
//    }
//
//    public BSTNode<T> getRight() {
//        return right;
//    }

    /***
     * Standard Constructor. Set the given data and make sure our children are null (for the moment).
     * @param datum
     */
    public BSTNode(T datum)
    {
        this.datum = datum;
        left = right = null;        // A sign of the C influence. Compound assignment
    }

    /**
     * Check if both children are null, if so this node is a leaf.
     * @return Whether or not this node is a leaf
     */
    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    /**
     * Differ to our datums toString as we cant tell the type. <T>
     * @return Textual representation of `datum`
     */
    @Override
    public String toString() {
        return datum.toString();
    }
}

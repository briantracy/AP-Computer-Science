/**
 *  A Binary Search Tree - BST
 *  This BST contains types that are comparable, as denoted by the conformance to the Comparable interface
 * @param <T> The class of the data in this tree.
 */
public class BST<T extends Comparable<T>>
{

    /**
     *      ======
     *      Fields
     *      ======
     */
    private BSTNode<T> root /* = null */;


    /**
     * Not much to do here as the root node is naturally null.
     */
    public BST()
    {

    }


    /**
     *      ================
     *      Accessor Method(s)
     *      ================
     */


    /**
     * This method is never used. Maybe remove from specification?
     * @return
     */
    private BSTNode<T> getTree() { return root; }

    /**
     * Im not sure how a whole tree could be a leaf, but I guess that just means whether or not the root node is a leaf.
     * @return
     */
    public boolean isLeaf()
    {
        return root.isLeaf();
    }

    /**
     * This method inserts a specific element into the tree. It first checks to see that we actually have a tree, if not
     * make that element the root. It then determines the exact location of the element by sequentially calling the compare
     * method on it to find whether or not is should be passed left or right. This method does NOT allow duplicates to
     * be inserted in the tree.
     * @param datum What to insert into the tree
     */
    public void insert(T datum)
    {
        if (root == null) {
            root = new BSTNode<T>(datum);
            return;
        }

        BSTNode<T> iter = root;

        for (;;) {
            if (iter.datum.compareTo(datum) < 0) {
                if (iter.left == null) {
                    iter.left = new BSTNode<T>(datum);
                    return;
                }
                iter = iter.left;
                continue;
            }
            else if (iter.datum.compareTo(datum) > 0) {
                if (iter.right == null) {
                    iter.right = new BSTNode<T>(datum);
                    return;
                }
                iter = iter.right;
                continue;
            }
            else {
                // no dupes
                return;
            }


         }
    }

    /**
     * Make this tree easily printable. Call personal print method then return empty string to make it look like
     * we are returning a textual representation.
     * @return
     */
    public String toString()
    {
        _print(root);
        return "";
    }


    /**
     *  This method simply prints the left node and right node of the current one. It also prints the current node.
     *
     * @param node Print this node
     */
    private void _print(BSTNode<T> node)
    {
        System.out.println(node);
        if (node.left != null) {
            _print(node.left);
        }
        if (node.right != null) {
            _print(node.right);
        }


    }

    /**
     *  Outward facing method for determining the depth of this tree. Calls the real method on the root, which is the
     *  most likely use case
     * @return Depth of this tree
     *
     * In a 1000 element tree, the usual depth is around 20-24. I know this because somewhere I have heard that doing
     * a binary search for names in a 1000 person phone book takes around 20 comparisons. There is a parallel somewhere in
     * there as the algorithm and data structure share the same name.
     */
    public int depth()
    {
        return _depth(root);
    }

    /**
     * Real method to determine depth. Checks to see first if the node is null. If it is not, then return 1 + the biggest
     * sub tree's depth. The 1 signifies the depth of the current node so each time this function is called on a new node,
     * we delve down one layer deeper.
     * @param node Node to find depth of
     * @return Depth of  `node`
     */
    private int _depth(BSTNode<T> node)
    {
        if (node != null) {
            return Math.max(_depth(node.left), _depth(node.right)) + 1;
        } else {
            return 0;   // empty tree has no depth
        }
    }

}

/**
 *      Algorithm Analysis
 *
 *      The worst case of finding an element in a BST is O(n)
 *      The best case is O(log n). This is the case for most operations. This is the reason BSTs are widely used.
 *      If the programmer knows that multiple types of operations will be used, BST is the way to go.
 *      The typical order of growth is O(log n) because this is how the BST is designed.
 *      Both inserting and Printing with my current implementation take O(log n) time.
 *
 *
 *
 *      Question: O(1) is called `constant time`. O(n) is called `linear time`. Does O(log n) have a special name. It seems
 *      very common. Would the name just be `logorithmic time`?
 *
 *
 */

import java.util.*;
/**
 *
 *  This is a fast priority queue. Unfortunately, It does not handle duplicates. All methods are O(log n) or O(1).
 *
 *  Each method has a description of what it does and how it is accomplished along with the time complexity of that method
 *  For more information see README.txt
 */
public class NonDuplicatingPriorityQueue<T>
{
    private NavigableSet<T> set;
    public NavigableSet<T> getSet() { return set; }
    private boolean isReveresed = false;

    public NonDuplicatingPriorityQueue(Comparator<T> comparator)
    {
        set = new TreeSet<T>(comparator);
    }

    /**
     *  <summary>
     *      This method will add an object into the correct place in the queue.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeSet.add
     *  </complexity>
     * @param elem  The element to be added to the queue
     * @return always true
     */
    boolean add(T elem)
    {
        return offer(elem);
    }

    /**
     * <summary>
     *     There is no difference between this method and the add method because priority queues have no maximum count.
     *     If this class did have a max, then this method would silently fail if there is no more space while the add method
     *     would raise an exception.
     * </summary>
     * <complexity>
     *     O(log n) - TreeSet.add
     * </complexity>
     * @param elem The element to be added to the queue
     * @return always true
     */
    boolean offer(T elem)
    {
        set.add(elem);
        return true;
    }

    /**
     *  <summary>
     *      This method is intended to return the first element in the queue. If the queue is empty, this method will
     *      throw an exception.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeSet.poll
     *  </complexity>
     * @return the first element in the queue.
     */
    T remove()
    {
        T elem = poll();
        if (elem != null) return elem;

        throw new NoSuchElementException("You Attempted To Remove An Element From An Empty Queue");
    }

    /**
     * <summary>
     *     This method also returns the first element in the queue. The difference between this method and remove is
     *     that this method will return null if the queue is empty as apposed to throwing an exception.
     *
     *     The reversecomparator method will affect this method. If the comparator is reversed, then remove the last
     *     element in the tree. If the comparator is normal, then return the first element.
     * </summary>
     * <complexity>
     *     O(log n) - TreeSet.poll
     * </complexity>
     * @return
     */
    T poll()
    {
        return isReveresed ? set.pollLast() : set.pollFirst();
    }

    /**
     *  <summary>
     *      This is the volatile version of peek. It will return a reference to the first element in the queue  without
     *      removing it. If the queue is empty, this method will throw an exception.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeSet.first / TreeSet.last
     *  </complexity>
     * @return A reference to the first object in the queue
     */
    T element()
    {
        T elem = peek();
        if (elem != null) return elem;

        throw new NoSuchElementException("Looked at empty queue");
    }

    /**
     * <summary>
     *     This is the non volatile way to look at the first element in a queue. It does not remove the first element,
     *     only returns it. If the queue is empty, this method returns null
     * </summary>
     * <complexity>
     *      O(log n) - TreeSet.first / TreeSet.last
     * </complexity>
     * @return The first element in the queue or null
     */
    T peek()
    {
        return isReveresed ? set.last() : set.first();
    }

    /**
     * <summary>
     *     This method returns true iff the queue is empty.
     * </summary>
     * <complexity>
     *     Constant Time. TreeSet.size is a cached variable like most Collections.size
     * </complexity>
     *
     */
    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    /**
     *  <summary>
     *      This method will remove an object from the queue.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeSet.remove
     *  </complexity>
     * @param o The element to remove from the queue
     */
    public void delete(T o)
    {
        set.remove(o);
    }

    /**
     *  <summary>
     *      This method will return the number of objects in the queue.
     *  </summary>
     *  <complexity>
     *      Constant - TreeSet.size is a cached variable like most Collections.size
     *  </complexity>
     * @return How many elements are in the queue
     */
    public int size()
    {
        return set.size();
    }

    /**
     *  <summary>
     *      This method will reverse the comparator of the queue.
     *
     *      This method is extremely efficient because of the way I have organized the state of the queue. At any point,
     *      the queue is in a reversed state, or is in a regular state. (See `isReversed` instance variable)
     *
     *      Regardless of the state of the queue, the physical comparator is never changed. Once assingned in the constructor,
     *      it cannot be mutated. The trick to reversing the comparator is in the implementation of poll.
     *
     *      The state of the comparator is only ever brought into question when items are being removed from the queue so only
     *      then do we have to check the value of `isReversed`.
     *
     *  </summary>
     *  <complexity>
     *      Constant - no comparisons are made, simply toggling a variable
     *  </complexity>
     *
     * @return The value of the new comparator
     */
    public boolean reverseComparator()
    {
        return (isReveresed = !isReveresed);
    }

    /**
     *  This is the algorithm to merge two queues. It relies on the tree merging algorithms of TreeSet.
     *  <complexity>
     *     Like most Tree Merging algorithms - Something not that great.
     *
     *  </complexity>
     * @param other
     */
    public void merge(NonDuplicatingPriorityQueue<T> other)
    {
        set.addAll(other.getSet());
    }

    /**
     * <summary>
     *      This method clears the priority queue by simply reassigning the instance. With modern memory management techniques
     *      used by GC's , this is efficient and safe. There are no tree-manipulations to bump up the Big O time.
     * </summary>
     * <complexity>
     *     Constant - simply reassign the instance
     * </complexity>
     */
    public void clear()
    {
        Comparator<? super T> myComparator = set.comparator();
        set = new TreeSet<T>(myComparator);
    }

    /**
     *  <summary>
     *      This method determines whether or not this queue contains the given key. This is done by simply checking if
     *      the underlying set has that object.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeSet.contains
     *  </complexity>
     * @param t The object to test
     * @return Whether or not   `t` is in the queue
     */
    public boolean contains(T t)
    {
        return set.contains(t);
    }
}

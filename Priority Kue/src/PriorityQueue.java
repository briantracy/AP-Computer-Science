import java.util.*;

/**
 *
 *  This priority queue is a fast priority queue that can handle duplicates. This is accomplished by mapping any duplicates
 *  to the original object. In most cases, all algorithms will perform in O(log n) time with the exception of the cases where
 *  duplicates are found.
 *
 *
 *
 */

/**
 *  A wrapper around the java.util.ArrayList<E> with a conventient way to remove and get at the last element.
 *
 *  Both of these methods perform in O(1) as removing the last element does not call for any shifting (a linear operation).
 *
 */
class FastList<E> extends ArrayList<E>
{
    public E getLast() { return get(size() - 1); }
    public E removeLast() { return remove(size() - 1); }
}

public class PriorityQueue<T>
{
    /**
     * This type maps [<#Object#> : <#DuplicatesOfObject#>]
     */
    private final NavigableMap<T, FastList<T>> map;
    private boolean isReveresed = false;

    /**
     *  <summary>
     *      Default Constructor
     *  </summary>
     * @param comparator The comparator to order this queue
     */
    public PriorityQueue(Comparator<T> comparator)
    {
        map = new TreeMap<T, FastList<T>>(comparator);
    }

    /**
     *  <summary>
     *      This is the same method as offer as there is no capacity restriction on this queue
     *      This method will add an object into the correct place in the queue.
     *      If this class did have a max, then this method would silently fail if there is no more space while the add method
     *     would raise an exception.
     *  </summary>
     *  <complexity>
     *      Best and Normal Case: O(log n) - TreeMap.put
     *      Worst Case (duplicates): O(log n) + O(duplicates)
     *  </complexity>
     * @param elem  The element to be added to the queue
     * @return always true
     */
    boolean add(T elem)
    {
        return offer(elem);
    }

    /**
     *  <summary>
     *      This method will add an object into the correct place in the queue.
     *  </summary>
     *  <complexity>
     *      Best and Average Case: O(log n) - TreeMap.put
     *      Worst Case (duplicates): O(log n) + O(duplicates)
     *  </complexity>
     * @param elem  The element to be added to the queue
     * @return always true
     */
    boolean offer(T elem)
    {
        /**
         * Will this element be a duplicate?
         */
        if (map.keySet().contains(elem)) {
            /**
             * We have a duplicate, put it in the correct duplicate array
             */
            if (map.get(elem) == null) {
                map.put(elem, new FastList<T>());
            }
            map.get(elem).add(0, elem); /// This causes the O(duplicates) performance
        }
        else {
            map.put(elem, null);
        }
        return true;
    }


    T remove()
    {
        T elem = poll();
        if (elem != null) return elem;

        throw new NoSuchElementException("You Attempted To Remove An Element From An Empty Queue");
    }

    /**
     *  <summary>
     *      This method will return and remove the first element in the queue. It is a more complex implementation if
     *      there are duplicates of the first element, but pretty straightforward if the first element is unique.
     *  </summary>
     *  <complexity>
     *      Best and Average Case: O(log n) - TreeMap.poll
     *      Worst (Duplicates): O(log n) + O(log n)  - TreeMap.poll + TreeMap.put
     *  </complexity>
     *
     * @return The first element in the queue
     */
    T poll()
    {
        Map.Entry<T, FastList<T>> entry = isReveresed ? map.pollLastEntry() : map.pollFirstEntry();

        /**
         * No Duplicates
         */
        if (entry.getValue() == null || entry.getValue().size() == 0) {
            return entry.getKey();
        }

        /**
         * We have a duplicate, a little more complicated...
         * First we store the key, as it is the first in the queue.
         * Then we remove the last element from the duplicate array, it will be the new key.
         * Then we put the duplicate array back in for the correct key.
         */
        else {
            T lastDup = entry.getValue().removeLast();
            T entryKey = entry.getKey();
            map.put(lastDup, entry.getValue());

            return entryKey;
        }
    }

    /**
     *  <summary>
     *      This is the volatile version of peek. It will return a reference to the first element in the queue  without
     *      removing it. If the queue is empty, this method will throw an exception.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeMap.entry
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
     *  <summary>
     *      This is the same method as element, but it will silenty fail instead of throwing an exception. If the queue is
     *      empty, this method will return null.
     *  </summary>
     *  <complexity>
     *      O(log n) - TreeMap.entry
     *  </complexity>
     * @return A reference to the first object in the queue
     */
    T peek()
    {
        return isReveresed ? map.lastEntry().getKey() : map.firstEntry().getKey();
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
        return map.isEmpty();
    }

    /**
     *  <summary>
     *      This method will delete an object from the queue.
     *      Note: Because this queue allows duplicates, this method will remove the first object it finds that is
     *      equal to the passed object.
     *  </summary>
     *  <complexity>
     *      This method is at worst: O(log n)
     *  </complexity>
     * @param o
     */
    public void delete(T o)
    {
        if (map.keySet().contains(o)) {
            if (map.get(o) == null || map.get(o).size() == 0) {
                map.remove(o);
            }
            else {
                T t = map.get(o).removeLast();
                map.put(t, map.get(o));
            }
        }
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
        return map.size();
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
}

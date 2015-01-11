import java.util.Comparator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 *
 *
 *
 *
 */
public class NonDuplicatingPriorityQueue<T>
{
    private final NavigableSet<T> set;
    private boolean isReveresed = false;

    public NonDuplicatingPriorityQueue(Comparator<T> comparator)
    {
        set = new TreeSet<T>(comparator);
    }

    boolean add(T elem)
    {
        return offer(elem);
    }

    boolean offer(T elem)
    {
        set.add(elem);
        return true;
    }

    T remove()
    {
        T elem = poll();
        if (elem != null) return elem;

        throw new NoSuchElementException("You Attempted To Remove An Element From An Empty Queue");
    }

    T poll()
    {

        return isReveresed ? set.pollLast() : set.pollFirst();
    }

    T element()
    {
        T elem = peek();
        if (elem != null) return elem;

        throw new NoSuchElementException("Looked at empty queue");
    }

    T peek()
    {
        return isReveresed ? set.last() : set.first();
    }

    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    public void delete(T o)
    {
        set.remove(o);
    }

    public int size()
    {
        return set.size();
    }

    public boolean reverseComparator()
    {
        return (isReveresed = !isReveresed);

    }
}

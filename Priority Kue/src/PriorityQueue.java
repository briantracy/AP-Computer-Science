import java.util.Comparator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class PriorityQueue<T>
{
    private final NavigableSet<T> set;

    public PriorityQueue(Comparator<T> comparator)
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
        return set.pollFirst();
    }

    T element()
    {
        T elem = peek();
        if (elem != null) return elem;
        throw new NoSuchElementException("Looked at empty queue");
    }

    T peek()
    {
        return set.first();
    }

    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    public void delete(T o)
    {
        set.remove(o);
    }

    @Override
    public String toString() {
        return set.toString();
    }
}

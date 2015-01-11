import java.util.*;

class FastList<E> extends ArrayList<E>
{
    public E getLast() { return get(size() - 1); }
    public E removeLast() { return remove(size() - 1); }
}

public class PriorityQueue<T>
{
    private final NavigableMap<T, FastList<T>> map;
    private boolean isReveresed = false;

    public PriorityQueue(Comparator<T> comparator)
    {
        map = new TreeMap<T, FastList<T>>(comparator);
    }

    boolean add(T elem)
    {
        return offer(elem);
    }

    boolean offer(T elem)
    {
        if (map.keySet().contains(elem)) {
            if (map.get(elem) == null) {
                map.put(elem, new FastList<T>());
            }
            map.get(elem).add(0, elem);
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
         */
        else {
            T ret = entry.getValue().removeLast();
            map.put(entry.getKey(), entry.getValue());

            return ret;
        }
    }

    T element()
    {
        T elem = peek();
        if (elem != null) return elem;

        throw new NoSuchElementException("Looked at empty queue");
    }

    T peek()
    {
        Map.Entry<T, FastList<T>> entry = isReveresed ? map.lastEntry() : map.firstEntry();

        if (entry.getValue() == null || entry.getValue().size() == 0) {
            return entry.getKey();
        }
        else {
            return entry.getValue().getLast();
        }
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

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

    public int size()
    {
        return map.size();
    }

    public boolean reverseComparator()
    {
        return (isReveresed = !isReveresed);

    }
}

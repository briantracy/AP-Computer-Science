import java.util.* ;

/**
 * This class represents a mathematical set. It is simply a Java set with a couple extra methods.
 *
 */
public class MathSet<E> implements Set<E> {

    /**
     * To implement Set<E>, An underlying set is needed to delegate most operations to.
     */
    private Set<E> mySet;

    /**
     * Default Constructor - creates the inner set that will be delegated to
     */
    public MathSet()
    {
        mySet = new HashSet<E>();
    }

    /**
     * These next two methods represents the two fundamental set operations. They are not modifiers, so they do not change
     * the instance they are called on. Instead, they return their result as a new MathSet instance. This allows the operations
     * to be chained, making complex set operations possible by compounding expressions. For example, assume A and B are MathSets
     *
     *      a ∪ b ∩ a - (a unioned with b) intersected with a
     */

    /**
     *  This method represents a union of two sets. Any objects in both sets are included in the final set. The final
     *  set is returned.
     * @param other The set to be unioned with
     * @return The union of two sets
     */
    public MathSet<E> union(MathSet<E> other)
    {
        MathSet<E> unioned = new MathSet<E>();
        unioned.addAll(mySet);
        unioned.addAll(other);

        return unioned;
    }

    /**
     *  This method represents an intersection of two sets. Any objects that are in both sets are included in the final
     *  set. The final set is returned.
     * @param other The set to be intersected with
     * @return The intersection of two sets.
     */
    public MathSet<E> intersect(MathSet<E> other)
    {
        MathSet<E> intersected = new MathSet<E>();

        for (E obj : other) {
            if (mySet.contains(obj)) {      // obj is in both sets
                intersected.add(obj);
            }
        }

        return intersected;
    }

    /**
     *
     * @return The Pretty Printed version of this set. Marshal the elements into rows so the output
     * doesn't take too much space.
     */
    @Override
    public String toString()
    {
        int width = (int)Math.sqrt(mySet.size());
        if (width == 0) return "{}";                // empty set

        StringBuilder str = new StringBuilder().append("{\n\t");

        int count = 1;

        for (E obj : this) {
            str.append("" + obj.toString() + ", ");     // print the current element then decide whether to break the row
            if (count < width) {
                count++;
            } else {
                count = 1;
                str.append("\n\t");
            }
        }
        return str.append("\n}").toString();
    }


    /**
     *  The following methods are required by the Set<E> Interface. Instead of implementing them myself, delegate
     *  them to the set instance of this class. This makes it so any functionality a set has, a MathSet has. By doing
     *  this, I have augmented the set class without the hassle of re-implementing all of its methods myself.
     */

    @Override
    public int size() { return mySet.size(); }
    @Override
    public boolean isEmpty() { return mySet.isEmpty(); }
    @Override
    public boolean contains(Object o) { return mySet.contains(o); }
    @Override
    public Iterator<E> iterator() { return mySet.iterator(); }
    @Override
    public Object[] toArray() { return mySet.toArray(); }
    @Override
    public <T> T[] toArray(T[] ts) { return mySet.toArray(ts); }
    @Override
    public boolean add(E e) { return mySet.add(e); }
    @Override
    public boolean containsAll(Collection<?> objects) { return mySet.containsAll(objects); }
    @Override
    public boolean addAll(Collection<? extends E> es) { return mySet.addAll(es); }
    @Override
    public boolean retainAll(Collection<?> objects) { return mySet.retainAll(objects); }
    @Override
    public boolean removeAll(Collection<?> objects) { return mySet.removeAll(objects); }
    @Override
    public void clear() { mySet.clear(); }
    @Override
    public boolean remove(Object obj) { return mySet.remove(obj); }
}

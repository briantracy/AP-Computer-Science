import java.util.* ;

public class MathSet<E> implements Set<E> {

    private Set<E> mySet;
    public Set<E> getObjects() { return mySet; }


    public MathSet()
    {
        mySet = new HashSet<E>();
    }

    public MathSet<E> union(MathSet<E> other)
    {
        MathSet<E> unioned = new MathSet<E>();
        unioned.addAll(mySet);
        unioned.addAll(other);

        return unioned;
    }

    public MathSet<E> intersect(MathSet<E> other)
    {
        MathSet<E> intersected = new MathSet<E>();

        for (E obj : other) {
            if (mySet.contains(obj)) {
                intersected.add(obj);
            }
        }

        return intersected;
    }

    /**
     *
     * @return The Pretty Printed version of this set.
     */
    @Override
    public String toString()
    {
        int width = (int)Math.sqrt(mySet.size());
        if (width == 0) return "{}";

        StringBuilder str = new StringBuilder().append("{\n\t");

        int count = 1;

        for (E obj : this) {
            str.append("" + obj.toString() + ", ");
            if (count % width == 0) {
                count = 1;
                str.append("\n\t");
            } else {
                count++;

            }

        }
        return str.append("\n}\n").toString();
    }









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

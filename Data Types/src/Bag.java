import java.util.HashMap;

public class Bag<T> {

    private HashMap<T, Integer> members;

    public Bag() {
        members = new HashMap<T, Integer>();
    }

    public Bag(T... ts) {
        this();
        add(ts);
    }

    public void add(T... ts) {
        for (T t : ts) {
            add(t);
        }
    }

    public boolean add(T t) {
        Integer val = members.get(t);
        return members.put(t, val == null ? 1 : val + 1) != null;
    }

    public int size() {
        return members.size();
    }

    public String toString() {
        StringBuilder b = new StringBuilder("{\n");

        for (T t : members.keySet()) {
            b.append(String.format("\t%s : %s\n", t, members.get(t)));
        }
        return b.append("}").toString();
    }
}

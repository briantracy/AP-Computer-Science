import java.util.HashMap;

public class Bag {

    private HashMap<Object, Integer> members;

    // lets lay it on you with the constructors

    public Bag() {
        members = new HashMap<Object, Integer>();
    }

    public Bag(int count) {
        members = new HashMap<Object, Integer>(count);
    }


    public Bag(Object[] array) {
        this(array.length);

        for (Object item : array) {
            addMember(item);
        }
    }



    /**
     *
     * @param i The Object to be added to the Bag
     * @return Returns true if the object is already in the bag.
     */
    public boolean addMember(Object i) {

        if (members.containsKey(i)) {
            /**
             * This Object already exists in the bag, increase its hit count
             */

            members.put(i, members.get(i) + 1);
            return true;
        }

        members.put(i, 1);
        return false;
    }

    public void addMembersFromBag(Bag other) {

    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("(\n");

        for (Object iter : members.keySet()) {
            builder.append("\t{ " + iter.toString() + " : " + members.get(iter) + " }\n");
        }

        builder.append(")\n");
        return builder.toString();
    }

}


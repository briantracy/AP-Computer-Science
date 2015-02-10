import java.util.*;

/**
 *  Showcasing the all powerful Collection interface. The collection interface defines methods that every object must
 *  implement to be considered part of the Collections api. Each collection can perform critical functions like
 *      add
 *      remove
 *      size
 *      isEmpty
 *      clear
 */
public class CollectionDemo
{

    /**
     *  Here we have a Collection of Collections that we will add some objects to. This demonstrates the mutual functionality
     *  shared by all Collections. Once we do that, because all collections are similar, we can find the max of each Collection.
     */
    public void demonstrate()
    {
        Collection<Collection> collections = new ArrayList<Collection>();

        collections.add(new ArrayList());
        collections.add(new PriorityQueue());
        collections.add(new HashSet());


        for (Collection collection : collections) {
            collection.add("Hello");
            collection.add("World");
            collection.add("9 * 9 = " + 9 * 9);
            collection.add("These are all collections that support the add() method");
        }

        for (Collection collection : collections) {
            System.out.print(collection + " Max = ");
            System.out.println(Collections.max(collection));
        }
    }
}

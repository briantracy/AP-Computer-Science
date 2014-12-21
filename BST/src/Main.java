import java.util.ArrayList;

import java.util.Collections;

/**
 * A simple test case / Workablel example. Creates a 1000 element BST and calculates the depth. Then prints it.
 */
public class Main {

    /**
     *
     * @Warning contains horrible variable names
     */
    public static void main(String[] args) {
        BST<Integer> t = new BST<Integer>();

        ArrayList<Integer> ints = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            ints.add(i);
        }
        Collections.shuffle(ints);

        for (int i = 0; i < 1000; i++) {
            t.insert(ints.get(i));
        }

        System.out.println(t.depth() + " Is the depth of the 1000 element tree");
        System.out.println(t + " \n That was our tree");
    }
}

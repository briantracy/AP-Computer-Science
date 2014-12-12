
public class Main
{
    /**
     *  A couple test cases to show the gist of the MathSet.
     *  Set a will contain evens while set b will contain odds.
     *
     */

    public static void main(String[] args)
    {
        MathSet<Integer> a = new MathSet<Integer>(), b = new MathSet<Integer>();

        for (int i = 0; i <= 10; i += 2) { a.add(i); }  // a contains evens up to 10
        for (int i = 1; i <= 9; i += 2)  { b.add(i); }  // b contains odds up to 9


        System.out.println("Intersection of A and B\n" + a.intersect(b)); // should be an empty set
        System.out.println("Union of A and B\n" + a.union(b));     // should be set of all numbers < 10)

        // These operations are chainable as they all return MathSets.

        MathSet<Integer> chain = a.union(b).intersect(a);
        System.out.println("The Union of A and B intersected with A\n" + chain);

        // Just for fun to highlight chainability

        MathSet<Integer> c = new MathSet<Integer>();
        c.add(11); c.add(12); c.add(-1);

        chain = chain.union(c).union(b).intersect(a.union(c).union(c)).intersect(chain.union(c));
        System.out.println("A very complicated expression yields " + chain);



    }
}

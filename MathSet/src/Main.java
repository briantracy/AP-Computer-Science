
public class Main
{


    public static void main(String[] args)
    {
        MathSet<Integer> a = new MathSet<Integer>(), b = new MathSet<Integer>();

        for (int i = 0; i <= 10; i += 2) { a.add(i); }  // a contains evens up to 10
        for (int i = 1; i <= 9; i += 2)  { b.add(i); }  // b contains odds up to 9


        System.out.println("Intersection of A and B\n" + a.intersect(b)); // should be an empty set
        System.out.println(a.union(b));     // should be set of all numbers < 10)
    }
}

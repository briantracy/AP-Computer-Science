import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GenericMax
{

    public static void main(String[] args)
    {
        Integer[] ints = new Integer[] { 1, 3, 5, 7, 9 };
        System.out.println(max(ints));

        String[] strings = _("hello", "goodbye", "zeds dead baby");
        String[] x = GenericMax.<String>_("hello");
        System.out.println(max(strings));
    }


    private static <T extends Comparable<T>> T max(T[] ts)
    {
        return Collections.max(Arrays.asList(ts));
    }

    private static <T extends Comparable<T>> T max(Collection<T> list)
    {
        return Collections.max(list);
    }

    private static <T> T[] _(T ... t)
    {
        return t;
    }
}

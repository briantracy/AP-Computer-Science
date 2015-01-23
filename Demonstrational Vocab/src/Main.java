import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        /**
         * Create the test cases and let them do their thing.
         */
        new Overloading     ().demonstrate();
        new Delegation      ().demonstrate();
        new Polymorphism    ().demonstrate();
        new Inheritance     ().demonstrate();
    }

    /**
     *
     * Draw a box.
     */
    public static void boxtext(String text) {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(text.split("\n")));
        StringBuilder all = new StringBuilder();
        int length = 0;
        for (String line : lines) {
            length = Math.max(length, line.length());
        }
        String format = "%-" + length + "s";
        char[] top = new char[length + 4];
        Arrays.fill(top, '\u2550');
        top[0] = '\u2554';
        top[top.length - 1] = '\u2557';
        all.append(top);
        all.append("\n");
        for (String line : lines) {
            all.append("\u2551 " + String.format(format, line) + " \u2551\n");
        }
        top[0] = '\u255A';
        top[top.length - 1] = '\u255D';
        all.append(top);
        System.out.println(all);
    }
}

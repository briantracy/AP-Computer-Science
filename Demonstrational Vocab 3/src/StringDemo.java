import java.util.Arrays;

/**
 *  Strings are the most versatile data type in Java. Represented as an array of unicode characters, they are used everywhere
 *  in every corner of the language. The String class provides an insane amount of functionality from getting acess to the
 *  underlying characters to pattern matching. The following method just scratches the surface of what the String can do.
 *
 *  http://docs.oracle.com/javase/tutorial/java/data/characters.html
 */
public class StringDemo
{

    private String string = "Hello World!";

    public void demonstrate()
    {
        System.out.println("The String          : " + string);
        System.out.println("The Characters      : " + Arrays.toString(string.toCharArray()));
        System.out.println("The Bytes           : " + Arrays.toString(string.getBytes()));
        System.out.println("Upper Case          : " + string.toLowerCase());
        System.out.println("Lower Case          : " + string.toUpperCase());
        System.out.println("First Character     : " + string.charAt(0));
        System.out.println("Last  Character     : " + string.charAt(string.length() - 1));
        System.out.println("Contains Hello?     : " + string.contains("Hello"));
        System.out.println("Contains World?     : " + string.contains("World"));
        System.out.println("Replace l with W    : " + string.replace('l', 'W'));
        System.out.println("[Done with String demo]\n");
    }
}

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] s = new String[]{ "hello", "goodbye", "hello" };

        Bag<String> b = new Bag<String>(s);

        b.add("hello", "goodbye");



        System.out.println(b);





    }
}

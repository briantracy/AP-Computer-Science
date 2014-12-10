import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        Bag b = new Bag(new Object[]{"Hello", "Hello", "Goodbye"});

        b.addMember(5);
        b.addMember(5);
        b.addMember(Arrays.toString(new Integer[]{1,2,3}));



        System.out.println(b);

    }
}

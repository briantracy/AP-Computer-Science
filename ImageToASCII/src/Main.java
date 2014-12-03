import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Missing Output Or Input File");
            System.exit(-1);
        }



        File input = new File(args[0]);
        File output = new File(args[1]);

        System.out.println("input = " + input);
        System.out.println("output = " + output);

        ASCIICreator asciiCreator = new ASCIICreator(input, output);

        asciiCreator.renderImage();

    }
}

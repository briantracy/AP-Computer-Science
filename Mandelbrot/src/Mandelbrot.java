/// Created by Brian Clement Tracy

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Mandelbrot {

    public static void main(String[] args) {
        Fractal fractal = new Fractal();

//        Complex c = new Complex(.1, .1);
//        c.escapeIters();

        //System.out.println(linesFromFile("Tokens.txt"));


    }


    static ArrayList<String> linesFromFile(String file)
    {
        ArrayList<String> strings = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }

        } catch (IOException e) {
            ;
        }

        return strings;
    }
}
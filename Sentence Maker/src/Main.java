/**
 * Created by Brian on 8/24/14.
 * example file /Users/Brian/Desktop/VocabList.txt
 *
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main
{

    
    private static void boxtext(String text) {

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
        all.append("\n");

        System.out.println(all);




    }
    public static void main(String[] args) throws FileNotFoundException
    {
        boxtext("Exercises");
        if (args.length > 0) {
            try {
                printMatches(linesFromFile(args[0]));
            } catch (FileNotFoundException ex) {
                System.out.println("File Passed was does not exist");
                ex.printStackTrace();
                throw ex;
            }

            for (int i  = 1; i < 22; i++) {
                System.out.println(i + ")");
            }

            return;




        }

        Scanner input = new Scanner(System.in);
        String word = null;


        welcome();

        ArrayList<String> list = new ArrayList<String>();


        while (!(word = input.nextLine()).equals("done")) {
            list.add(word);
        }

        long time = System.currentTimeMillis();

        printMatches(list);
        exit(time);

    }



    private static void welcome()
    {
        boxtext("Hello and Welcome to the word matcher.\nEnter a list of words you would like to pair.\nFinish the list with 'done'");
    }

    private static void exit(long ms)
    {
        boxtext("Done - " + (System.currentTimeMillis() - ms) + "ms");


    }



    private static void printMatches(ArrayList<String> x)
    {
        ArrayList<WordPair> words = new ArrayList<WordPair>();
        int length = x.size();


        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                WordPair pair = new WordPair(x.get(i), x.get(j));
                boolean shouldAdd = true;

                if (pair.a.equals(pair.b)) {
                    shouldAdd = false;
                }

                for (WordPair in : words)
                {
                    if (in.equals(pair)) {
                        shouldAdd = false;
                    }
                }

                if (shouldAdd) {
                    words.add(pair);
                }
            }
        }
        int index = 1;
        for (WordPair p : words)
        {
            System.out.println(String.format("%-2d) ", index) + p);
            index++;
        }


    }

    private static ArrayList<String> linesFromFile(String filePath) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(filePath));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine())
        {

            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

}



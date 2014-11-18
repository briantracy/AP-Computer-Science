/**
 *  You can ignore the first half of this file as it was given to us by the Java tutorial site.
 *  The exercise code begins at line 45 in the main method and continue until the end of the file.
 *
 *  The first exercise was to write a method that reads a file and converts it into numbers that are stored in an ArrayList.
 *  The method I wrote is called readFile() and it takes a string as the path to the file that should be read. This method is incredibly
 *  resilient to failure as it has several try catches to account for any possible errors that might come up. Some possible errors that might
 *  arise are errors due to the file that I am trying to open not existing. The second error that could be encountered is when an invalid
 *  string is attempted to be converted to an int.
 *
 *  The second exercise was to recreate the cat command as a function that took a file as an argument and then printed it out. The
 *  code to accomplish this was provided, but it did not compile as there were several uncaught exceptions. The second assignment was to
 *  edit the program to make it compile. I accomplished this by changing the method signature and adding a catch block to an existing try.
 *
 */


import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ListOfNumbers {
    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(new Integer(i));
    }
    public void writeList() {
        PrintWriter out = null;
        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("OutFile.txt"));

            for (int i = 0; i < SIZE; i++)
                out.println("Value at: " + i + " = " + list.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                    e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }

    public static void main(String[] args)
    {
        ListOfNumbers l = new ListOfNumbers();


        l.readList("InFile.txt");


    }


    /**
     *  This method will read numbers from a file and store them in an ArrayList. The numbers must be formatted
     *  one per line in the file. Feel free to include malformed inputs in the file such as blank spaces or non
     *  numbers. These will prove no match form the might of a well placed try {} catch{}
     * @param filePathString A string that represents the relative file path the the numbers file.
     */
    public void readList(String filePathString)
    {
        list.clear();   // flush the list of anything that it had in it before.


        /**
         * This try here is necessary because instantiating file readers and buffered readers may cause an
         * exception to be thrown if the file we are trying to get at does not exist.
         */
        try {
            FileReader fileReader = new FileReader(filePathString);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                /**
                 * We must use a nested try here because there is another exception we must watch out for.
                 * In order to make this routine more robust, any malformed inputs (ones that cannot be converted
                 * into integers by Integer.valueOf()) will be ignored as apposed to crashing the program.
                 */
                try {
                    Integer number = Integer.valueOf(line);     // attempt to coerce an integer out of the current line
                    list.add(number);                           // if we got here, we could successfully create an integer from the line


                } catch (NumberFormatException num) {
                    System.err.println("There was a malformed input on the line containing [\"" + line + "\"]");
                    // alert the user that there was a malformed input and show them what it was.
                }

            }


        } catch (IOException ioexception) {
            System.err.println("The file you attempted to open either did not exist or could not be opened.");
        }

        System.out.println(list);       // to prove that we successfully read from a file into an array, print the array.
    }


    /**
     * Cat is a UNIX command that stands for concatenate. By calling it on a single file ie: `cat file.txt`, it will print the contents
     * of the file to stdout. This implementation will print the contents of the file that is given. Alone, it did not compile. Our job was to make it compile.
     * @param file the file to be concatenated (cat)
     * @throws IOException
     */
    public static void cat(File file) throws IOException {
        // In order to silence the warnings about "uncaught IOExceptions" due to closing the file,
        // we put `throws IOException` in the method signature


        RandomAccessFile input = null;
        String line = null;

        /**
         * Possibility of a FileNotFound exception
         */
        try {

            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("The file you passed did not exist or there was trouble reading it");
        }
        finally {
            if (input != null) {
                input.close();      // here we have another possible IOException, thus the change to the method signature
            }
        }
    }

}
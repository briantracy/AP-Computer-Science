import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.Hashtable;

public class QuestionBank
{

    private Dictionary<String, Boolean> entries;



    public QuestionBank() {

        entries = new Hashtable<String, Boolean>();


        try {
            BufferedReader buff = new BufferedReader(new FileReader("Questions.txt"));

            String line = null;

            while ((line = buff.readLine()) != null) {
                String[] pair = line.split(",");

                String question = pair[0];
                Boolean answer   = pair[1].equals("true");

                entries.put(question, answer);


            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println(entries);
        ask(null);

    }


    public String ask(String question)
    {
        String key = null;
        while ((key = entries.keys().nextElement()) != null) {
            System.out.println(key);
        }

        return null;

    }


}

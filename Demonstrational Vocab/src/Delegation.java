import java.util.HashMap;
import java.util.Map;


/**
 *  This class demonstrates delegation. This is done by providing functionality to clients but letting a secret inner
 *  object do all of the heavy lifting. For example, this class represents an English Language Dictionary that holds entries
 *  in the form <word>:<definition>. This format is perfect for a Map<String, String>, so we can delegate to that instead of
 *  re-inventing the wheel.
 */
class EnglishLanguageDictionary
{
    /**
     * Here is the variable we are delegating to. It holds all of the data for this dictionary.
     */
    private Map<String, String> dict;

    /**
     *  Each of the following methods adds critical functionality to the EnglishLanguageDictionary class yet not much
     *  code needs to be written because of the power of delegation.
     */
    public EnglishLanguageDictionary() {
        dict = new HashMap<String, String>();
    }
    public void addWordWithDefinition(String word, String definition)
    {
        dict.put(word, definition);
    }
    public String getDefinitionOfWord(String word)
    {
        return dict.get(word);
    }
    public void printEnglishLanguageDictionary()
    {
        for (String key : dict.keySet()) {
            System.out.println(key + " = " + dict.get(key) + ".");
        }
    }
}

public class Delegation
{
    public void demonstrate()
    {
        Main.boxtext("Demonstrating Delegation");

        /// Create an EnglishLanguageDictionary
        EnglishLanguageDictionary dictionary = new EnglishLanguageDictionary();


        /// each of the following calls are not applicable to a Map, but through the use of delegation,
        /// the EnglishLanguageDictionary can appropriately use a Map to get the job done.
        dictionary.addWordWithDefinition("Phone", "A wireless communication device");
        dictionary.addWordWithDefinition("Dog", "A small mammal");
        dictionary.addWordWithDefinition("Boat", "A water vehicle");
        dictionary.printEnglishLanguageDictionary();

        System.out.println("The definition of a boat is: " + dictionary.getDefinitionOfWord("Boat"));
    }
}

/**
 *  The following is an interface. It provides a means to augment the public interface of a class. Any class that
 *  implements this interface will have the method myCount() added to its public interface.
 *
 *  All methods declared in an interface are public.
 *
 *  When two or more objects both implement a ICountable, an interface is created between them, allowing other classes
 *  to communicate with them in a mutually understood manner.
 */
interface ICountable
{
    int myCount();
}

class CoolString implements ICountable
{
    String data;

    @Override
    public int myCount() {
        return data.length();
    }
}

class CoolNumber implements ICountable
{
    Integer data;

    @Override
    public int myCount() {
        return data;
    }
}

public class Interface
{
    public void demonstrate()
    {
        Main.header("Demonstrating An Interface");
        /// Two Completely different types, yet they both can have myCount() called on them
        CoolString coolString = new CoolString();
        CoolNumber coolNumber = new CoolNumber();

        coolString.data = "Hello World";
        coolNumber.data = 10;

        if (coolString.myCount() > coolNumber.myCount()) {
            System.out.println("The string had a bigger myCount");
        }
        else {
            System.out.println("The number had a bigger myCount");
        }
    }
}



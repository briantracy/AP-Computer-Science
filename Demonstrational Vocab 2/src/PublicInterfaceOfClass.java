import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  This class is used as a demonstration of the public interface of a class. A class's public interface is made
 *  up of all of its public methods and fields. It is how this class is seen and interacted with by the world.
 *
 *  Things that are not included in the public interface of a class are all private and protected methods and fields.
 *
 */
public class PublicInterfaceOfClass
{
    public void publicInterface1()
    {
        /// this method is part of the public interface because it has the `public` access modifier
    }

    public void publicInterface2(String str)
    {
        /// this method is part of the public interface because it has the `public` access modifier
    }

    private void notInPublicInterface()
    {
        /// this method is not part of the public interface because it has the `private` access modifier
        /// therefore, it can only be seen within this class.
    }

    protected void notInPublicInterface2()
    {
        /// this method is not part of the public interface because it has the `protected` access modifier
        /// therefore, it can only be seen within this class and its subclasses
    }

    /// This field will be part of the public interface because it has the `public` access modifier
    public String publicString;

    /// This field will not be a part of the public interface because it has the `private` access modifier so it is
    /// only visible inside of its class.
    private Object privateObject;


    /**
     *  This method uses reflection to textualize the public interface of this class.
     */
    public void demonstrate() {
        Main.header("Demonstrating The Public Interface of a Class");
        System.out.println("The following methods and fields are the public interface of this class");

        Class cls = PublicInterfaceOfClass.class;

        Method[] methods = cls.getMethods();
        Field[] fields = cls.getFields();

        for (Method method : methods) {
            System.out.println(method);
        }
        for (Field field : fields) {
            System.out.println(field);
        }

    }
}

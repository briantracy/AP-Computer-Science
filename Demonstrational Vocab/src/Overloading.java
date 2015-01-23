/**
 *  This class demonstrates overloading. Overloading a function is making many functions with the same name
 *  but different signatures. Method signatures can be changed by number, type, and order of arguments.
 *
 *  The following example class contains three different methods all with the same name. In this context, method() is
 *  overloaded. The correct version of method() will be called depending on the arguments given.
 */


public class Overloading {

    private void method(int ArgumentOne)
    {
        System.out.println("In method with one argument");
        System.out.println("The value of argument is " + ArgumentOne);
        System.out.println();
    }

    private void method(int ArgumentOne, int ArgumentTwo)
    {
        System.out.println("In method with two arguments");
        System.out.println("The values of both arguments are " + ArgumentOne + ", " + ArgumentTwo);
        System.out.println();
    }

    private void method(String stringArgumentOne)
    {
        System.out.println("In method with one argument, but its type is String");
        System.out.println("The value of the string argument is " + stringArgumentOne);
        System.out.println();
    }

    public void demonstrate()
    {
        Main.boxtext("Demonstrating Overloading");

        /// The compiler knows exactly which method() to call because there is no ambiguity in the method signatures.

        /// Calling method with one integer argument
        method(10);

        /// Calling method with two integer arguments
        method(15, 15);

        /// Calling method with one String argument
        method("[A String Argument]");

    }


}

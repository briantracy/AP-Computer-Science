import java.lang.reflect.Method;


/**
 * This class allows the beautiful `mapcar` subroutine of LISP a chance at the limelight in the world of Java.
 *
 * USAGE OF EXEC FUNCTION
 * This method is supposed to look as much like the lisp edition as possible. When calling, simply provide the name
 * of the method you want to call on each object in the provided list, the types of the parameters and the parameters themselves.
 *
 * To call this method, simply use MapCar.exec("function", "classes", "params", "list"). This is designed to be as close as possible
 * to the lisp solution.
 *
 *
 * I made use of the wonderful Reflection API's offered. According to wikipedia, reflection is
 *
 *      he ability of a computer program to examine (see type introspection) and modify
 *      the structure and behavior (specifically the values, meta-data, properties and
 *      functions) of the program at runtime.
 *
 *  The most relevant aspect of this is examining (calling in our case) the functions of an object at runtime. With this
 *  power, we can mimic a closure provided by other languages as simply the name of a method that we want to represent. This
 *  name is nothing more than a String because in Java, we cannot pass functions around as stated in the description of the exec method.
 */
public class MapCar
{

    /**
     * <summary>
     *     This method fill execute a "fake" closure on each element of the given list. This is a "fake" closure because
     *     in java, functions are not first class objects. They cannot be referred to by reference, therefore, cannot be passed
     *     around in and out of functions.
     * </summary>
     *
     * @param methodName The name of the function to be executed on each element of `objects`
     * @param paramClasses The classes of each argument that `methodName` needs, if any
     * @param params The parameters that `methodName` needs, if any
     * @param objects The objects that `methodName` will be called on
     * @return The result of calling `methodName` on each element of `objects`
     */
    public static Object[] exec(String methodName, Class[] paramClasses, Object[] params, Object[] objects)
    {
        /** Sanitize this input */

        assert methodName   != null : "That's not a method";
        assert objects      != null : "Can't call functions on null objects";


        if (paramClasses != null && params != null) {
            assert paramClasses.length == params.length : "Unbalanced method call. Unequal parameters required and passed.";
        }


        /** The results list. Created by the return values created by each element in  `objects`. */

        Object[] returnValues = new Object[objects.length];

        /** Iterate through each element in the list and call the function on it. Accumulate the results in the `returnValues` list */

        for (int i = 0; i < objects.length; i++)
        {
            Object currentObject = objects[i];
            Method method;

            try {
                /**
                 *  Here we are seeing wether or not this "closure" can really be applied to the current object
                 * */

                method = currentObject.getClass().getMethod(methodName, paramClasses);

            } catch (NoSuchMethodException nsme) {

                System.err.println("Method " + methodName + " does not exist for " + currentObject);

                returnValues[i] = null;
                continue;
            }

            try {

                /**
                 *  Attempt to call the closure on the current object. Hopefully, nothing blows up in our face.
                 */
                returnValues[i] = method.invoke(currentObject, params);

            } catch (Exception e) {
                System.err.println("Method " + methodName + " could not be invoked for" + currentObject);
                returnValues[i] = null;
            }

        }

        return returnValues;
    }
}

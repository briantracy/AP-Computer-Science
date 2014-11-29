import java.util.Arrays;

public class MapCar
{


    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(mapcar("hello", $("hello"), $("Heello", 5))));

    }




    private static Object[] $(Object... args)
    {
        return args;
    }

    private static Object[] list(Object... args)
    {
        return $(args);
    }


    private static Object[] mapcar(String selector, Object[] args, Object[] list)
    {
        


        return list;
    }




}
